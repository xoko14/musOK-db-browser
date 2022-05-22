package com.musok.musokdbbrowser.api.connection

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.musok.musokdbbrowser.api.exceptions.IncorrectLoginException
import com.musok.musokdbbrowser.api.exceptions.InternalServerErrorException
import com.musok.musokdbbrowser.api.exceptions.UnknownException
import com.musok.musokdbbrowser.api.exceptions.UserAlreadyRegisteredException
import com.musok.musokdbbrowser.api.mappings.auth.Token
import com.musok.musokdbbrowser.api.mappings.legal.Legal
import com.musok.musokdbbrowser.api.mappings.song.Song
import com.musok.musokdbbrowser.api.mappings.song.SongStatus
import com.musok.musokdbbrowser.api.mappings.user.User
import kong.unirest.Unirest

object Server {
    var url: String? = null
    private var token: Token? = null

    fun isLoggedIn(): Boolean{
        return token != null
    }

    //<editor-fold desc="Auth">
    fun logIn(user: String, password: String) {
        val response = Unirest.post("$url/token")
            .header("accept", "application/json")
            .field("username", user)
            .field("password", password)
            .asJson()

        when(response.status){
            200 -> token = Gson().fromJson(response.body.toString(), Token::class.java)
            401 -> throw IncorrectLoginException()
            else -> throw UnknownException()
        }

    }
    //</editor-fold>

    //<editor-fold desc="User">
    fun getAllUsers(skip: String = "0", limit: String = "100"): List<User>{
        val response = Unirest.get("$url/users")
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()

        val itemType = object: TypeToken<List<User>>() {}.type

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), itemType)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun createUser(username: String, hashedPwd: String): User{
        val response = Unirest.post("$url/users/")
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"username\": \"$username\",\n" +
                        "  \"password\": \"$hashedPwd\"\n" +
                        "}")
                .asJson()

        when(response.status){
            200 -> return Gson().fromJson(response.body.toString(), User::class.java)
            400 -> throw UserAlreadyRegisteredException()
            else -> throw UnknownException()
        }
    }

    fun getCurrentUser(): User {
        val response = Unirest.get("$url/users/me")
            .header("Authorization", token?.getHeaderValue())
            .header("accept", "application/json")
            .asJson()

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), User::class.java)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun getCurrentUserUploaded(skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/users/me/uploaded")
                .header("Authorization", token?.getHeaderValue())
                .header("accept", "application/json")
                .queryString("skip", skip)
                .queryString("limit", limit)
                .asJson()

        val itemType = object: TypeToken<List<Song>>() {}.type

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), itemType)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun getCurrentUserFavs(skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/users/me/favs")
            .header("Authorization", token?.getHeaderValue())
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()

        val itemType = object: TypeToken<List<Song>>() {}.type

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), itemType)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun getUser(id: String): User{
        val response = Unirest.get("$url/users/$id")
            .header("accept", "application/json")
            .asJson()

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), User::class.java)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun getUserUploaded(id: String, skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/users/$id/uploaded")
                .header("Authorization", token?.getHeaderValue())
                .header("accept", "application/json")
                .queryString("skip", skip)
                .queryString("limit", limit)
                .asJson()

        val itemType = object: TypeToken<List<Song>>() {}.type

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), itemType)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun getUserFavs(id: String, skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/users/$id/favs")
            .header("Authorization", token?.getHeaderValue())
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()

        val itemType = object: TypeToken<List<Song>>() {}.type

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), itemType)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }
    //</editor-fold>

    //<editor-fold desc="Songs">
    fun getAllSongs(skip: String = "0", limit: String = "100"): List<Song>{
        val response = Unirest.get("$url/songs/")
            .header("accept", "application/json")
            .queryString("skip", skip)
            .queryString("limit", limit)
            .asJson()

        val itemType = object: TypeToken<List<Song>>() {}.type

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), itemType)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun createSong(){
        TODO()
    }

    fun getSong(id: String): Song{
        val response = Unirest.get("$url/songs/$id")
            .header("accept", "application/json")
            .asJson()

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), Song::class.java)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    //TODO: GET SONG MEDIA

    fun favSong(id: String): SongStatus{
        val response = Unirest.put("$url/songs/$id/fav")
            .header("Authorization", token?.getHeaderValue())
            .header("accept", "application/json")
            .asJson()

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), SongStatus::class.java)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    fun unfavSong(id: String): SongStatus{
        val response = Unirest.put("$url/songs/$id/unfav")
            .header("Authorization", token?.getHeaderValue())
            .header("accept", "application/json")
            .asJson()

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), SongStatus::class.java)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }
    //</editor-fold>

    //<editor-fold desc="legal">

    fun getLegal(): Legal{
        val response = Unirest.get("$url/legal")
            .header("accept", "application/json")
            .asJson()

        when(response.status) {
            200 -> return Gson().fromJson(response.body.toString(), Legal::class.java)
            500 -> throw InternalServerErrorException()
            else -> throw UnknownException()
        }
    }

    //</editor-fold>
}