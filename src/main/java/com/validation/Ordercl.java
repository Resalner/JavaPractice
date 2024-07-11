@Data
@NoArgsConstructor
public class orderDto {

    @NotNull
    private Long id;

    @NotBlank
    private String customerName;

    @NotBlank
    @Email
    private String customerEmail;

    @NotEmpty
    private List<PizzaItem> pizzaItems;

    @NotNull
    @Positive
    private BigDecimal totalAmount;

    @NotNull
    private OrderStatus status;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    private LocalDateTime updatedAt;
}

public enum OrderStatus {
    Pending,
    Processing,
    Completed,
    Cancelled
}
