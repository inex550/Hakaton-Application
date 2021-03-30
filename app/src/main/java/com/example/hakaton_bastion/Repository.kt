package com.example.hakaton_bastion

import com.example.hakaton_bastion.models.network.Place
import com.example.hakaton_bastion.models.network.Point
import com.example.hakaton_bastion.models.network.User

object Repository {

    lateinit var currentUser: User

    val users = listOf(
        User("Бедняга работяга", "token1", "00000", 0),
        User("Босс", "token2", "89012", 1),
        User("Бог", "token3", "87452", 2)
    )

    val places = mutableListOf(
        Place(
            "Школа №10", mutableListOf(
                Point("Кабинет №3", "04:7D:74:AA:ED:6C:80", false),
                Point("Лаборатория", "04:CF:75:AA:ED:6C:80", false),
                Point("Кабинет директора", "04:CB:75:AA:ED:6C:80", false)
            )
        ),
        Place(
            "Тестовая площадка", mutableListOf(
                Point("Точка 1", "04:CF:75:AA:ED:6C:80", false),
                Point("Точка 2", "04:CB:75:AA:ED:6C:80", false),
                Point("Точка 3", "04:7D:74:AA:ED:6C:80", false)
            )
        ),

        Place(
            "Мебельный магазин", mutableListOf(
                Point("Отдел с диванами", "04:CB:75:AA:ED:6C:80", false),
                Point("Отдел с диванами", "04:7D:74:AA:ED:6C:80", false),
                Point("Склад", "04:CF:75:AA:ED:6C:80", false)
            )
        )
    )
}