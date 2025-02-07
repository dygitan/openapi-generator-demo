package github.tanpatrick.openapi.demo

import github.tanpatrick.openapi.demo.generated.model.CreateUserDto
import github.tanpatrick.openapi.demo.generated.model.UserDto
import org.springframework.stereotype.Service

@Service
class UserService {

    private val users: List<UserDto> = listOf(
        UserDto(firstName = "John", lastName = "Doe", id = 1),
        UserDto(firstName = "Jane", lastName = "Doe", id = 2)
    )

    fun createUser(createUserDto: CreateUserDto): UserDto {
        val user = UserDto(firstName = createUserDto.firstName, lastName = createUserDto.lastName, id = (users.size + 1).toLong())
        users.toMutableList().add(user)
        return user;
    }

    fun getUsers(): List<UserDto> {
        return users
    }
}