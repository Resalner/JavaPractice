public interface UserService {
    boolean registerUser(@Valid UserDto userDto);
    boolean loginUser(@NotBlank String email, @NotBlank String password);
    boolean leaveReview(@Valid UserDto userDto, @NotBlank String pizza, @NotBlank String review);
    boolean selectPizza(@Valid UserDto userDto, @NotBlank String pizza);
    boolean updatePizzaAvailability(@NotBlank String pizzaName, boolean isAvailable, @Valid UserDto userDto);
}

public interface EmailService {
    void sendConfirmationEmail(String name, String email);
}
