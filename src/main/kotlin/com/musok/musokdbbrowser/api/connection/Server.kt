package com.musok.musokdbbrowser.api.connection

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.musok.musokdbbrowser.api.mappings.auth.Token
import com.musok.musokdbbrowser.api.mappings.song.Song
import com.musok.musokdbbrowser.api.mappings.song.SongStatus
import com.musok.musokdbbrowser.api.mappings.user.User
import kong.unirest.Unirest

object Server {
    var url: String? = null
    lateinit var token: Token
        private set

    //<editor-fold desc="Auth">
    fun logIn(user: String, password: String) {
        val response = Unirest.post("$url/token")
            .header("accept", "application/json")
            .field("username", user)
            .field("password", password)
            .asJson()
            .body.toString()


        token = Gson().fromJson(response, Token::class.java)
    }
    //</editor-fold>

    //<editor-fold desc="User">
    fun getAllUsers(skip: String = "0", limit: String = "100"): List<User>{
        val response = Unirest.get("$url/users")
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()
            .body.toString()

        val itemType = object: TypeToken<List<User>>() {}.type
        return Gson().fromJson(response, itemType)
    }

    fun createUser(){
        TODO()
    }

    fun getCurrentUser(): User {
        val response = Unirest.get("$url/users/me")
            .header("Authorization", token.getHeaderValue())
            .header("accept", "application/json")
            .asJson()
            .body.toString()
        return Gson().fromJson(response, User::class.java)
    }

    fun getCurrentUserFavs(skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/users/me/favs")
            .header("Authorization", token.getHeaderValue())
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()
            .body.toString()
        val itemType = object: TypeToken<List<Song>>() {}.type
        return Gson().fromJson(response, itemType)
    }

    fun getUser(id: String): User{
        val response = Unirest.get("$url/users/$id")
            .header("accept", "application/json")
            .asJson()
            .body.toString()
        return Gson().fromJson(response, User::class.java)
    }

    fun getUserFavs(id: String, skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/users/$id/favs")
            .header("Authorization", token.getHeaderValue())
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()
            .body.toString()
        val itemType = object: TypeToken<List<Song>>() {}.type
        return Gson().fromJson(response, itemType)
    }
    //</editor-fold>

    //<editor-fold desc="Songs">
    fun getAllSongs(skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/songs/")
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()
            .body.toString()

        val itemType = object: TypeToken<List<Song>>() {}.type
        return Gson().fromJson(response, itemType)
    }

    fun createSong(){
        TODO()
    }

    fun getSong(id: String): Song{
        val response = Unirest.get("$url/songs/$id")
            .header("accept", "application/json")
            .asJson()
            .body.toString()
        return Gson().fromJson(response, Song::class.java)
    }

    //TODO: GET SONG MEDIA

    fun favSong(id: String): SongStatus{
        val response = Unirest.put("$url/songs/$id/fav")
            .header("Authorization", token.getHeaderValue())
            .header("accept", "application/json")
            .asJson()
            .body.toString()
        return  Gson().fromJson(response, SongStatus::class.java)
    }

    fun unfavSong(id: String): SongStatus{
        val response = Unirest.put("$url/songs/$id/unfav")
            .header("Authorization", token.getHeaderValue())
            .header("accept", "application/json")
            .asJson()
            .body.toString()
        return  Gson().fromJson(response, SongStatus::class.java)
    }
    //</editor-fold>

}