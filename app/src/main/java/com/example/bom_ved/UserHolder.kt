package com.example.bom_ved


import androidx.annotation.DrawableRes


object UserHolder {
    private val picturesName = arrayOf(
        "TYLER 1",
        "Владимир Владимирович Путин",
        "Виталий Громяко (Arthas)",
        "Александр Стюарт Мейен",
        "Стивен Пол Джобс",
        "Софья Васильевна Корвин-Круковская",
        "Чжан Имин",
        "Ли Бён Чхоль",
        "Марк Эллиот Цукерберг",
        "Николай Огородников",
    )

    private val pictureImages = arrayOf(
        "https://cdn-images.win.gg/resize/w/620/format/webp/type/progressive/fit/cover/path/wp/uploads/2021/08/tyler1-reaches-challenger-rank-using-only-jungle-champions.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/19/Vladimir_Putin_11-10-2020_%28cropped%29.jpg/250px-Vladimir_Putin_11-10-2020_%28cropped%29.jpg",
        "https://memepedia.ru/wp-content/uploads/2018/08/papich8.jpg",
        "https://assets.puzzlefactory.pl/puzzle/285/118/thumb.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b9/Steve_Jobs_Headshot_2010-CROP.jpg/274px-Steve_Jobs_Headshot_2010-CROP.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Sofja_Wassiljewna_Kowalewskaja_1.jpg/240px-Sofja_Wassiljewna_Kowalewskaja_1.jpg",
        "https://leonardo.osnova.io/26ebafbe-b52e-8218-91b8-c9eb18e1f99f/-/preview/1100/-/format/webp/",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/4/46/Lee_Byung-chul_%28crop%29.jpg/249px-Lee_Byung-chul_%28crop%29.jpg",
        "https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Mark_Zuckerberg_F8_2019_Keynote_%2832830578717%29_%28cropped%29.jpg/250px-Mark_Zuckerberg_F8_2019_Keynote_%2832830578717%29_%28cropped%29.jpg",
        "https://sun9-32.userapi.com/impf/c845521/v845521513/fe57c/QyKsxBhkkcw.jpg?size=1620x2160&quality=96&sign=415859aa5ac4def60ad8e4674edee909&type=album",
    )

    private val pictureDate = arrayOf(
        "1998 - ...",
        "1952 - ...",
        "1990 - ...",
        "1984 - ...",
        "1955 - 2011",
        "1850 - 1891",
        "1974 - ...",
        "1910 - 1987",
        "1984 - ...",
        "2001 - ...",
    )

    private val pictureInformation = arrayOf(
        "Тайлер Стейнкамп - более известный как Tyler1 — американский стример на Twitch. " +
                "Является одним из наиболее популярных стримеров League of Legends, его Twitch " +
                "насчитывает чуть более четырёх миллионов подписчиков. Стейнкампу было " +
                "запрещено играть в LoL",
        "Владимир Владимирович Путин — российский государственный, политический и военный деятель. " +
                "Действующий президент Российской Федерации и верховный главнокомандующий Вооружёнными силами " +
                "Российской Федерации с 7 мая 2012 года.",
        "14 сентября Виталий Arthas Цаль, также известный как Папич, представил фанатам картину " +
                "«Величайший». Художник изобразил стримера в образе Wraith King, а также оставил " +
                "на полотне более десяти отсылок к истории стримов Цаля.",
        "Александр Стюарт Мейен в сотрудничестве с волонтёрами создал игру под названием YandereDev." +
                "Александр Стюарт Мейен невероятный человек, создавший невероятную игру - симуляцию реальности (альтернативнвое восприятие мира)" +
                "Александр Стюарт Мейен заявлял желание изменить название на LoveSick, " +
                "но пока эта идея не стала реальностью.",
        "В конце 1970-х годов Стив Джобс и его друг Стив Возняк разработали один из первых " +
                "персональных компьютеров, обладавший большим коммерческим потенциалом. " +
                "Компьютер Apple II стал первым массовым продуктом компании Apple",
        "3 января родилась, 17 — крещена София; родители её — Артиллерии полковник Василий " +
                "Васильевич сын Круковской и законная жена его Елизавета Федоровна; муж " +
                "православного исповедания, а жена лютеранского. Восприемник: отставной " +
                "Артиллерии подпоручик Семен Васильевич сын Круковской и провиантмейстера " +
                "Василия Семёновича сына Круковского дочь девица Анна Васильевна.",
        "В 2019 году ни об одном развлекательном приложении не говорили так часто и громко, " +
                "как о китайском TikTok. Он с одинаковым успехом завоевал американских, " +
                "европейских и азиатских подростков, объединив их в огромное сообщество " +
                "внутри себя. До этого это не удавалось никому, включая Фейсбук, Инстаграм или Твиттер.",
        "Изначально Ли Бён Чхоль не был слишком успешным бизнесменом. 1 марта 1938 года он основал" +
                " компанию, названную Samsung Trading Co. — это была предшественница Samsung. " +
                "Название Samsung означало «три звезды», они были изображены на первоначальном корпоративном логотипе.",
        "Марк родился 14 мая 1984 года в городе Уайт-Плейнс (штат Нью-Йорк), что в нескольких " +
                "километрах к северу от города Нью-Йорка. Отец — стоматолог Эдвард Цукерберг " +
                "(по состоянию на 2012 год продолжал практику). Мать — психиатр Карен Цукерберг. " +
                "Его бабушки и дедушки были из Германии, Австрии и Польши. Был вторым ребёнком и " +
                "единственным мальчиком из 4 детей в семье; его сёстры — Рэнди (старшая), Донна и Ариэль.",
        "Николай Огородников почетный выпускник школы №21 имени смачнай бебры г. Рязани закончивший с отличием.",

    )

    private val pictureGender = arrayOf(
        "Мужчина",
        "Мужчина",
        "Мужчина",
        "Мужчина",
        "Мужчина",
        "Женщина",
        "Мужчина",
        "Мужчина",
        "Мужчина",
        "Мужчина",
    )

    fun createCollectionPictures(): List<Picture> {
        val pictures = mutableListOf<Picture>()
        for (i in 0..9){
            val picture = Picture(
                picturesName[i],
                pictureImages[i],
                pictureDate[i],
                pictureInformation[i],
                pictureGender[i]
            )
            pictures.add(picture)
        }
        return pictures
    }
}

data class Picture(
    val Name: String,
    val imageURL: String,
    val Date: String,
    val Information: String,
    val Gender: String,
)