@Data
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Min(1)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Email 
    private String email;

    @NotBlank
    @Size(min = 3, max = 50)
    private String password;

    @NotBlank
    private String role;
}

