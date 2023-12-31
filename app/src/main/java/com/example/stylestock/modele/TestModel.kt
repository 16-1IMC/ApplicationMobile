package com.example.stylestock.modele

var Adidas = Brand(
    1,
    "Adidas",
    "Adidas is a German multinational corporation, founded and headquartered in Herzogenaurach, Germany, that designs and manufactures shoes, clothing and accessories. It is the largest sportswear manufacturer in Europe, and the second largest in the world, after Nike.",
    Image(
        1,
        "https://logos-marques.com/wp-content/uploads/2020/04/Adidas-logo.png",
        "29/06/2023"
    ),
    Image(
        2,
        "https://newsolez.com/wp-content/uploads/2018/02/banner_addidas_originals_promo_b7ad4407-7dd0-4ca9-8881-29d04bbda68a_1600x681-e1563918699775.png",
        "30/06/2023"
    ),
    "adidas@gmail.com",
    listOf(
        SocialNetwork(
            1,
            "instagram",
            "https://www.instagram.com/adidas/",
            "25/04/2548",
        ),
        SocialNetwork(
            2,
            "facebook",
            "https://www.facebook.com/adidas",
            "25/04/2548",
        ),
        SocialNetwork(
            3,
            "twitter",
            "https://twitter.com/adidas",
            "25/04/2548",
        ),
    ),
    listOf(
        Category(
            id = 1,
            name = "Sport",
        ),
        Category(
            id = 2,
            name = "Casual",
        ),
        Category(
            id = 3,
            name = "Streetwear",
        ),
    ),
    emptyList(),
    "30/08/1596",
    "active",
)

var AdidasPost = Post(
    1,
    "New Aero Shoes",
    "New Aero Shoes are here, pre-order now ! only 1000 pieces available",
    Adidas,
    listOf(
        Image(
            1,
            "https://img.freepik.com/photos-gratuite/sneaker-qui-porte-mot-nike_123827-23436.jpg",
            "29/06/2023"
        ),
        Image(
            2,
            "https://assets.adidas.com/images/w_600,f_auto,q_auto/bfff95f0750341bf939daed70141260e_9366/Chaussure_NMD_V3_Blanc_GX9468_01_standard.jpg",
            "29/06/2023"
        ),
    ),
)