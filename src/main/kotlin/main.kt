fun main() {
  println(calculationCommission(222))
  //println(likePeople(3))
//    println(discount(990, false))
//    println(calculationHour(1))
//    println(calculationMin(34))
//    println(agoToText(111_111))
 //   println(commissionCard(transfer = 11000.0, previous = 6_000.0))
}

fun commissionCard(typeCard: String = "МИР", transfer: Double, previous: Double = 0.0): String {

    val dayLimit = 150_000.0
    val monthLimit = 600_000.0


    // Проверка лимитов перевода
    if (transfer > dayLimit) return "Превышен дневной лимит перевода!"
    if (previous + transfer > monthLimit) return "Превышен месячный лимит перевода!"

    return when (typeCard) {
        "Mastercard" -> {
            val monthlyFreeLimit = 75_000
            // Если сумма предыдущих переводов превышает лимит
            if (previous >= monthlyFreeLimit) {
                val commission = transfer * 0.006 + 20
                "Комиссия за перевод: $commission руб."
            } else if (previous + transfer > monthlyFreeLimit) {
                // Комиссия начисляется только на сумму, превышающую 75 000 руб.
                val chargeableAmount = previous + transfer - monthlyFreeLimit
                val commission = chargeableAmount * 0.006 + 20
                "Комиссия за перевод: $commission руб."
            } else {
                "Комиссии нет"
            }
        }

        "Visa" -> {
            val commission = transfer * 0.0075
            if (commission < 35) {
                "Комиссия за перевод: 35 руб."
            } else {
                "Комиссия за перевод: $commission руб."
            }
        }

        "МИР" -> "Комиссии нет"

        else -> "Неверный тип карты"
    }
}


fun calculationCommission(amount: Int): Double {
    val commission = 0.0075
    val minCommission = 35

    return if (amount * commission < minCommission) minCommission.toDouble() else amount * commission
}

fun likePeople(like: Int) = when {
    like % 10 == 1 && like % 100 != 11 -> "понравилось $like человеку"
    like % 10 in 2..4 && like % 100 !in 12..14 -> "понравилось $like человекам"
    else -> "понравилось $like людям"
}


fun discount(count: Int, monthlyBuyer: Boolean): String {
    val hundred = 100
    val regular = 0.01
    val discountFive = 0.05

    return when {
        count in 0..1_000 -> {
            "Нет скидки! Ваша покупка составляет: $count"
        }

        count in 1001..10_000 && monthlyBuyer -> {
            val always = (count - hundred) - ((count - hundred) * regular)
            "Ваша скидка $always   и того со скидкой  ${count - always} рублей "
        }

        count in 1001..10_000 && !monthlyBuyer -> {
            "Ваша скидка $hundred   и того со скидкой  ${count - hundred} рублей "
        }

        count > 10_001 && monthlyBuyer -> {
            val always = count * discountFive + ((count - count * discountFive) * regular)
            "Ваша скидка $always    и того со скидкой  ${count - always} рублей "
        }

        else -> "ваша скидка ${count * discountFive} и того со скидкой  ${count - (count * discountFive)}"
    }
}

fun calculationMin(min: Int): String {

    return when {
        min % 100 in 11..14 -> "$min минут назад"
        min % 10 == 1 -> "$min минуту назад"
        min % 10 in 2..4 -> "$min минуты назад"
        else -> "$min минут назад"
    }

}


fun calculationHour(hour: Int): String {
    return when {
        hour % 100 in 11..14 -> "$hour часов назад"
        hour % 10 == 1 -> "$hour час назад"
        hour % 10 in 2..4 -> "$hour часа назад"
        else -> "$hour часов назад"
    }
}

fun agoToText(second: Int): String {
    val minutes = 60
    val hours = 3600

    return when (second) {
        in 0..60 -> "был(а) только что"
        in 61 until 60 * 60 -> calculationMin(second / minutes)
        in 60 * 60 + 1 until 24 * 60 * 60 -> calculationHour(second / hours)
        in 24 * 60 * 60 until 24 * 60 * 60 * 2 -> " «был(а) вчера»"
        in 24 * 60 * 60 * 2 until 24 * 60 * 60 * 3 -> "  «был(а) позавчера»"
        else -> "был(а) давно"
    }
}




