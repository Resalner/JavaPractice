@Data
@NoArgsConstructor
@Validated
public class PizzaDto {

    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotBlank
    @Size(min = 10, max = 200)
    private String description;

    @NotBlank
    @Size(min = 10, max = 20)
    private String category;
}
