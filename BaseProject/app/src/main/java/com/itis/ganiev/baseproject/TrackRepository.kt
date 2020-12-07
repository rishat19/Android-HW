package com.itis.ganiev.baseproject

object TrackRepository {

    val trackList: List<Track> = arrayListOf(
        Track(0, "ABBA", "Happy New Year", R.drawable.abba, R.raw.abba),
        Track(1, "Frank Sinatra", "Let It Snow", R.drawable.sinatra, R.raw.sinatra),
        Track(2, "Wham!", "Last Christmas", R.drawable.wham, R.raw.wham),
        Track(3, "Sia", "Ho Ho Ho", R.drawable.sia, R.raw.sia),
        Track(4, "Стекловата", "Новый Год", R.drawable.steklovata, R.raw.steklovata),
        Track(5, "Ляпис Трубецкой", "Огоньки", R.drawable.lyapis, R.raw.lyapis)
    )

}