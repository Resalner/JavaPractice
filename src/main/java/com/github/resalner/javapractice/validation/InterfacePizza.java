public interface PizzaService {
    List<PizzaDto> getAllPizzas();

    @NotNull
    PizzaDto getPizzaById(@NotNull @Positive Long id);

    @NotNull
    PizzaDto createPizza(@Valid @NotNull PizzaDto pizza);

    @NotNull
    PizzaDto updatePizza(@NotNull @Positive Long id, @Valid @NotNull PizzaDto pizza);

    void deletePizza(@NotNull @Positive Long id);
}
