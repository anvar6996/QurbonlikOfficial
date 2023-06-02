package uz.univer.qurbonlikofficial.data.model

data class SheepParametrs(
    val paremetrsSheepByKg: ArrayList<String> = arrayListOf<String>(
        "Qo'y raqami", "Ism familiya", "Telefon raqam", "Og'irligi", "Berilgan pul", "Qarzi"
    ), val paremetrsSheepByHead: ArrayList<String> = arrayListOf<String>(
        "Familiya", "Ism", "Telefon raqam", "Qarzi", "Narxi"
    )
)