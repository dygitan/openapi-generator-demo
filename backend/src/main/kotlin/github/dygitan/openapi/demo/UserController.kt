package github.dygitan.openapi.demo

import github.dygitan.openapi.demo.generated.api.UserApi
import github.dygitan.openapi.demo.generated.model.CreateUserDto
import github.dygitan.openapi.demo.generated.model.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class UserController(
    private val userService: UserService
) : UserApi {

    override fun createUser(createUserDto: CreateUserDto): ResponseEntity<UserDto> {
        return ResponseEntity.ok(userService.createUser(createUserDto))
    }

    override fun getUsers(): ResponseEntity<List<UserDto>> {
        return ResponseEntity.ok(userService.getUsers())
    }
}