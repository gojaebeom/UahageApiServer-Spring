package com.uahage.api.service;

import com.uahage.api.domain.User;
import com.uahage.api.domain.UserDetail;
import com.uahage.api.domain.UserImage;
import com.uahage.api.dto.ReqEditUserDto;
import com.uahage.api.dto.ReqJoinDto;
import com.uahage.api.dto.ResJoinDto;
import com.uahage.api.dto.ResShowUserDto;
import com.uahage.api.repository.UserDetailRepository;
import com.uahage.api.repository.UserImageRepository;
import com.uahage.api.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class UserService {

    private UserRepository userRepository;
    private UserDetailRepository userDetailRepository;
    private UserImageRepository userImageRepository;
    private TokenService tokenService;
    private S3Service s3Service;

    public ResJoinDto join(ReqJoinDto reqJoinDto) {
        User user = userRepository.findByEmail(reqJoinDto.getEmail());
        System.out.println(user);

        if(user == null){
            // 회원 가입
            Short duplicatedCount = userRepository.countByNickname(reqJoinDto.getNickname());
            if(duplicatedCount > 0){
                throw new IllegalArgumentException("닉네임이 중복되었습니다.");
            }

            user = reqJoinDto.toUser();
            userRepository.save(user);

            UserDetail userDetail = reqJoinDto.toUserDetail(user);
            userDetailRepository.save(userDetail);
        }

        String act = tokenService.createAct(user.getId());

        return ResJoinDto.builder()
                .accessToken(act)
                .refreshToken(act)
                .build();
    }

    public void edit(ReqEditUserDto reqEditUserDto) throws Exception {
        User user = userRepository.findById(reqEditUserDto.getId());
        if(user == null) throw new IllegalArgumentException("일치하는 유저가 없습니다");

        if(reqEditUserDto.getImageInit() != null && reqEditUserDto.getImageInit() == 'Y'){
            log.info("[ 이미지 초기화 요청 -> s3 서버에서 이미지 삭제, DB 이미지 레코드 삭제 ]");
            List<UserImage> userImages = userImageRepository.findAllByUserId(reqEditUserDto.getId());

            System.out.println(userImages);

            for(UserImage userImage : userImages){
                String[] imagePaths = userImage.getImagePath().split("/");
                s3Service.delete("profiles/".concat(imagePaths[imagePaths.length-1]));
                userImageRepository.deleteById(userImage.getId());
            }
        }else{
            log.info("[ 이미지 업로드 요청 -> s3 서버에 이미지 업로드, DB에 이미지 이름 등록 ]");
            List<UserImage> userImages = new ArrayList<>();

            if(reqEditUserDto.getImages() != null){
                log.info("[ 업로드할 이미지 파일 존재 ]");
                List<String> fileNames = s3Service.upload("profiles", reqEditUserDto.getId(), reqEditUserDto.getImages());

                for(String fileName : fileNames){
                    UserImage userImage = UserImage.builder()
                            .user(user)
                            .imagePath(fileName)
                            .build();
                    userImages.add(userImage);
                }
                System.out.println(userImages);
                userImageRepository.saveAll(userImages);
            }
        }

        user = reqEditUserDto.toUser();
        if(user.getNickname() != null)
            userRepository.edit(user);

        UserDetail userDetail = reqEditUserDto.toUserDetail(user);
        if(userDetail.getAgeGroupType() != null || userDetail.getBabyBirthday() != null || userDetail.getBabyGender() != null)
            userDetailRepository.edit(userDetail);
    }

    public void destroy(Long id) {
        log.info("[ 회원 탈퇴 요청 -> 탈퇴 처리 ]");
        userRepository.destroyById(id);
    }

    public Boolean verifyDuplicateNickname(String nickname) {
        String[] words = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅣ", "ㅢ", "ㅔ", "ㅖ"};
        for (String word : words)
            if (nickname.contains(word))
                throw new IllegalArgumentException("올바르지 않은 형식입니다.");

        // 닉네임 정규식
        final String PATTERN = "!/^[a-zA-Zㄱ-힣0-9]*$/";
        if (Pattern.matches(PATTERN, nickname))
            throw new IllegalArgumentException("닉네임에 공백 또는 특수문자을 사용할 수 없습니다.");

        if (nickname.length() < 2 || nickname.length() > 12)
            throw new IllegalArgumentException("닉네임은 2-12자리 사이로 입력해주세요.");

        final String slangStrings = "10새,10새기,10새리,10세리,10쉐이,10쉑,10스,10쌔,10쌔기,10쎄,10알,10창,10탱,18것,18넘,18년,18노,18놈,18뇬,18럼,18롬,18새,18새끼,18색,18세끼,18세리,18섹,18쉑,18스,18아,ㄱㅐ,ㄲㅏ,ㄲㅑ,ㄲㅣ,ㅅㅂㄹㅁ,ㅅㅐ,ㅆㅂㄹㅁ,ㅆㅍ,ㅆㅣ,ㅆ앙,ㅍㅏ,凸,갈보,갈보년,강아지,같은년,같은뇬,개같은,개구라,개년,개놈,개뇬,개대중,개독,개돼중,개랄,개보지,개뻥,개뿔,개새,개새기,개새끼,개새키,개색기,개색끼,개색키,개색히,개섀끼,개세,개세끼,개세이,개소리,개쑈,개쇳기,개수작,개쉐,개쉐리,개쉐이,개쉑,개쉽,개스끼,개시키,개십새기,개십새끼,개쐑,개씹,개아들,개자슥,개자지,개접,개좆,개좌식,개허접,걔새,걔수작,걔시끼,걔시키,걔썌,걸레,게색기,게색끼,광뇬,구녕,구라,구멍,그년,그새끼,냄비,놈현,뇬,눈깔,뉘미럴,니귀미,니기미,니미,니미랄,니미럴,니미씹,니아배,니아베,니아비,니어매,니어메,니어미,닝기리,닝기미,대가리,뎡신,도라이,돈놈,돌아이,돌은놈,되질래,뒈져,뒈져라,뒈진,뒈진다,뒈질, 뒤질래,등신,디져라,디진다,디질래,딩시,따식,때놈,또라이,똘아이,똘아이,뙈놈,뙤놈,뙨넘,뙨놈,뚜쟁,띠바,띠발,띠불,띠팔,메친넘,메친놈,미췬,미췬,미친,미친넘,미친년,미친놈,미친새끼,미친스까이,미틴,미틴넘,미틴년,미틴놈,바랄년,병자,뱅마,뱅신,벼엉신,병쉰,병신,부랄,부럴,불알,불할,붕가,붙어먹,뷰웅,븅,븅신,빌어먹,빙시,빙신,빠가,빠구리,빠굴,빠큐,뻐큐,뻑큐,뽁큐,상넘이,상놈을,상놈의,상놈이,새갸,새꺄,새끼,새새끼,새키,색끼,생쑈,세갸,세꺄,세끼,섹스,쇼하네,쉐,쉐기,쉐끼,쉐리,쉐에기,쉐키,쉑,쉣,쉨,쉬발,쉬밸,쉬벌,쉬뻘,쉬펄,쉽알,스패킹,스팽,시궁창,시끼,시댕,시뎅,시랄,시발,시벌,시부랄,시부럴,시부리,시불,시브랄,시팍,시팔,시펄,신발끈,심발끈,심탱,십8,십라,십새,십새끼,십세,십쉐,십쉐이,십스키,십쌔,십창,십탱,싶알,싸가지,싹아지,쌉년,쌍넘,쌍년,쌍놈,쌍뇬,쌔끼,쌕,쌩쑈,쌴년,썅,썅년,썅놈,썡쇼,써벌,썩을년,썩을놈,쎄꺄,쎄엑,쒸벌,쒸뻘,쒸팔,쒸펄,쓰바,쓰박,쓰발,쓰벌,쓰팔,씁새,씁얼,씌파,씨8, 씨끼,씨댕,씨뎅,씨바,씨바랄,씨박,씨발,씨방,씨방새,씨방세,씨밸,씨뱅,씨벌,씨벨,씨봉,씨봉알,씨부랄,씨부럴,씨부렁,씨부리,씨불,씨붕,씨브랄,씨빠,씨빨,씨뽀랄,씨앙,씨파,씨팍,씨팔,씨펄,씸년,씸뇬,씸새끼,씹같,씹년,씹뇬,씹보지,씹새,씹새기,씹새끼,씹새리,씹세,씹쉐,씹스키,씹쌔,씹이,씹자지,씹질,씹창,씹탱,씹퇭,씹팔,씹할,씹헐,아가리,아갈,아갈이,아갈통,아구창,아구통,아굴,얌마,양넘,양년,양놈,엄창,엠병,여물통,염병,엿같,옘병,옘빙,오입,왜년,왜놈,욤병,육갑,은년,을년,이년,이새끼,이새키,이스끼,이스키,임마,자슥,잡것,잡넘,잡년,잡놈,저년,저새끼,접년,젖밥,조까,조까치,조낸,조또,조랭,조빠,조쟁이,조지냐,조진다,조찐,조질래,존나,존나게,존니,존만,존만한,좀물,좁년,좆,좁밥,좃까,좃또,좃만,좃밥,좃이,좃찐,좆같,좆까,좆나,좆또,좆만,좆밥,좆이,좆찐,좇같,좇이,좌식,주글,주글래,주데이,주뎅,주뎅이,주둥아리,주둥이,주접,주접떨,죽고잡,죽을래,죽통,쥐랄,쥐롤,쥬디,지랄,지럴,지롤,지미랄,짜식,짜아식,쪼다,쫍빱,찌랄,창녀,캐년,캐놈,캐스끼,캐스키,캐시키,탱구,팔럼,퍽큐,호로,호로놈,호로새끼,호로색,호로쉑,호로스까이,호로스키,후라들,후래자식,후레,후뢰,씨ㅋ발,ㅆ1발,씌발,띠발,띄발,뛰발,띠ㅋ발,뉘뮈";
        // 비속어 필터링
        String[] slangList = slangStrings.split(",");
        for (String slang : slangList)
            if (nickname.contains(slang))
                throw new IllegalArgumentException("비속어를 포함할 수 없습니다.");

        return userRepository.countByNickname(nickname) == 0 ? true : false;
    }

    public Boolean verifyDuplicateEmail(String email) {
        if(email == null || email == ""){
            throw new IllegalArgumentException("이메일이 입력되지 않았습니다.");
        }
        return userRepository.countByEmail(email) == 0 ? true : false;
    }

    public ResShowUserDto show(Long userId) {
        ResShowUserDto resShowUserDto = userRepository.findOneJoinWithUserDetailAndUserImageById(userId);
        System.out.println(resShowUserDto);

        if(resShowUserDto == null) throw new IllegalArgumentException("id와 일치하는 회원을 찾을 수 없습니다.");

//        UserDetail userDetail = userDetailRepository.findByUserId(userId);
//        List<UserImage> userImages = userImageRepository.findAllByUserId(userId);

        return resShowUserDto;
    }
}