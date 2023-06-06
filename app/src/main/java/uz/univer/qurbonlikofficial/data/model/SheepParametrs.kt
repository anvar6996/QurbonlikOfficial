package uz.univer.qurbonlikofficial.data.model

data class SheepParametrs(
    val paremetrsSheepByKg: ArrayList<String> = arrayListOf<String>(
        "Қўй рақами", "Исм фамилияси", "Харидор телефон рақами", "Қўй огирлиги", "Тўланган сумма", "Қарзи"
    ), val paremetrsSheepByHead: ArrayList<String> = arrayListOf<String>(
        "Қўй рақами","Фамилия", "Исм", "Харидор телефон рақами", "Қарзи", "Нархи","Тўланган сумма"
    )
)