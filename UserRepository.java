public interface UserService {
   
    boolean createUser(UserDto userDto);

    
    boolean updateUser(Long userId, UserDto userDto, UserDto userDto);

    UserDto getUserDetails(Long userId, UserDto userDto);

   
    List<UserDto> getAllUsers(UserDto userDto);

    boolean deactivateUser(Long userId, UserDto userDto);


    boolean reactivateUser(Long userId, UserDto userDto);
}
