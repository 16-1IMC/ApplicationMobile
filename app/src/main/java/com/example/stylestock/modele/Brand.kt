package com.example.stylestock.modele

data class Brand(
    val id: Int = 0,
    val name: String = "",
    val description: String="",
    val profilePicture: Image = Image(0,"https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/2048px-Minecraft_missing_texture_block.svg.png",""),
    val banner: Image = Image(0,"https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/2048px-Minecraft_missing_texture_block.svg.png",""),
    val email: String ="",
    val socialNetworks: List<SocialNetwork> = emptyList(),
    val categories: List<Category>  = emptyList(),
    val posts: List<Post> = emptyList(),
    val created_at: String = "",
    val status: String = "",
)
data class BrandAll(
    val id: Int,
    val name: String,
    val description: String="",
    val profilePicture: Image = Image(0,"https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/2048px-Minecraft_missing_texture_block.svg.png",""),
    val banner: Image = Image(0,"https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Minecraft_missing_texture_block.svg/2048px-Minecraft_missing_texture_block.svg.png",""),
    val email: String ="",
    val socialNetworks: List<String> = emptyList(),
    val categories: List<Category>  = emptyList(),
    val posts: List<String> = emptyList(),
    val created_at: String = "",
    val status: String = "",
)