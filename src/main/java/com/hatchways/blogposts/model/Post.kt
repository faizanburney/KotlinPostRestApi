package com.hatchways.blogposts.model

import javax.persistence.*
import java.util.*

@Entity
@Table(name = "post")
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
        @SequenceGenerator(name = "post_sequence", sequenceName = "post_sequence", initialValue = 5)
        val id: Long? = null,

        @Column
        var text: String? = null,

        @Column
        var tags: String? = null,

        @Column
        var likes: Long? = null,

        @Column
        var reads: Long? = null,

        @Column(precision = 7, scale = 2)
        var popularity: Float? = null,

        @ManyToMany
        @JoinTable(
                name = "user_post",
                joinColumns = [JoinColumn(name = "post_id")],
                inverseJoinColumns = [JoinColumn(name = "user_id")]
        )
        var users: Set<User>? = null
) {
  fun getTagsArray(): Array<String> {
    return tags?.split(",")?.toTypedArray() ?: arrayOf()
  }

  fun setTagsArray(tagsArray: Array<String>) {
    tags = tagsArray.joinToString(",")
  }
}