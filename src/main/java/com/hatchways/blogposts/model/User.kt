package com.hatchways.blogposts.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["username"]),
        UniqueConstraint(columnNames = ["password"])
    ]
)
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", initialValue = 6)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    private var username: String,

    @Column(nullable = false)
    private var password: String,

    @ManyToMany(mappedBy = "users")
    var posts: Set<Post>? = null
) : UserDetails {

    constructor() : this("", "")

    constructor(username: String, password: String) : this(
        id = null,
        username = username,
        password = password,
        posts = null
    )

    override fun getAuthorities(): Collection<GrantedAuthority>? = null

    override fun getPassword(): String = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}