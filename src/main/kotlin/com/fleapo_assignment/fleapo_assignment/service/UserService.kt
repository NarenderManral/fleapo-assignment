package com.fleapo_assignment.fleapo_assignment.service
import com.fleapo_assignment.fleapo_assignment.model.LoginRequest
import com.fleapo_assignment.fleapo_assignment.model.UserDto
import com.fleapo_assignment.fleapo_assignment.model.User
import com.fleapo_assignment.fleapo_assignment.repository.UserRepository
import org.springframework.stereotype.Service
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import java.util.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Service
class UserService(@Autowired private val userRepository: UserRepository) {

    private val passwordEncoder = BCryptPasswordEncoder()

    fun createUser(userDto: UserDto): User {
        require(userRepository.findByEmail(userDto.email) == null) { "Email already exists" }
        val hashedPassword = passwordEncoder.encode(userDto.password)
        val user = User(email = userDto.email, password = hashedPassword, role = userDto.role)
        return userRepository.save(user)
    }

    fun authenticateUser(loginRequest: LoginRequest): String {
        val user = userRepository.findByEmail(loginRequest.email) ?: throw IllegalArgumentException("User not found")
        require(passwordEncoder.matches(loginRequest.password, user.password)) { "Invalid credentials" }
        return generateJwtToken(user)
    }

    private fun generateJwtToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour expiration
            .signWith(SignatureAlgorithm.HS256, "secretKey") // Use a proper secret key here
            .compact()
    }
}
