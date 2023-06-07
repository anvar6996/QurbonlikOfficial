package uz.univer.qurbonlikofficial.data.model

data class SheepParametrs(
    val paremetrsSheepByKg: ArrayList<String> = arrayListOf<String>(
        "Қўй рақами",//0
        "Фамилия",//1
        "Исм",//2
        "Харидор телефон рақами",//3
        "Қўй нархи",//4
        "Тўланган сумма",//5
        "Қарзи"//6
    ), val paremetrsSheepByHead: ArrayList<String> = arrayListOf<String>(
        "Қўй рақами",//0
        "Фамилия",//1
        "Исм",//2
        "Харидор телефон рақами",//3
        "Қўй Нархи",//4
        "Тўланган сумма",//5
        "Қарзи"//6
    )
)