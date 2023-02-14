data class Notes(
    val id: Int = 0,
    val title: String = "",
    val text: String = "",
    val isDelete: Boolean = false,
    val privacy: Int = 0,
    val commentPrivacy: Int = 0,
)

data class Comments(
    val id: Int = 0,
    val noteId: Int = 0,
    val message: String = "",
    val isDelete: Boolean = false
)

