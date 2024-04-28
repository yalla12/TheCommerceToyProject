package thecommerceproject.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberRequestDto {

    @ApiModelProperty(value = "회원 아이디 ", required = true)
    @NotBlank(message = "회원 아이디를 입력해주세요")
    private String memberId;

    @ApiModelProperty(value = "회원 비밀번호", required = true)
    @NotBlank(message = "비밀번호를 입력해주세요")
    private String memberPwd;

    @ApiModelProperty(value = "회원 닉네임 (닉네임은 2자 이상 10자 이하로 입력해주세요)", required = true)
    @NotBlank(message = "닉네임을 입력해주세요")
    @Size(min = 2, max = 10, message = "닉네임은 2자 이상 10자 이하로 입력해주세요")
    private String nickname;

    @ApiModelProperty(value = "회원 이름", required = true)
    @NotBlank(message = "이름을 입력해주세요")
    private String name;

    @ApiModelProperty(value = "회원 휴대폰 번호 (지정된 형식으로 전화번호를 입력해주세요 ex)01012345678)", required = true)
    @NotBlank(message = "휴대폰 번호를 입력해주세요")
    @Pattern(regexp = "(01[016789])(\\d{3,4})(\\d{4})", message = "올바른 휴대폰 번호를 입력해주세요.")
    private String phoneNumber;

    @ApiModelProperty(value = "회원 이메일 (지정된 형식으로 이메일을 입력해주세요 ex)xxx@xxx.com)", required = true)
    @NotBlank(message = "이메일 주소를 입력해주세요")
    @Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", message = "올바른 이메일 주소를 입력해주세요")
    private String email;
}
