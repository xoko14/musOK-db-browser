package com.musok.musokdbbrowser.api

import com.musok.musokdbbrowser.api.connection.Server
import com.musok.musokdbbrowser.api.mappings.song.FavStatus
import org.junit.jupiter.api.Test
import org.mindrot.jbcrypt.BCrypt

class APITests {
    @Test
    fun loginAndGetCurrentUser(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")
        val user = Server.getCurrentUser()
        println(user.username)
        assert(user.username == "creator")
    }

    @Test
    fun getAllUsers(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")
        val users = Server.getAllUsers()
        println(users.size)
        assert(true)
    }

    @Test
    fun getUserTestuser(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")
        val user = Server.getUser("1")
        assert(user.username == "testuser")
    }

    @Test
    fun getAllSongs(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")
        val songs = Server.getAllSongs()
        println(songs.size)
        assert(true)
    }

    @Test
    fun getSongBrainPower(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")
        val song = Server.getSong("1")
        println(song.artURL)
        assert(song.songName == "Brain Power")
    }

    @Test
    fun favAndUnfavTest(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")

        val favedStatus = Server.favSong("1")
        assert(favedStatus.status == FavStatus.FAVED && favedStatus.song.songName == "Brain Power")

        val unfavedStatus = Server.unfavSong("1")
        assert(unfavedStatus.status == FavStatus.UNFAVED && unfavedStatus.song.songName == "Brain Power")
    }

    @Test
    fun getCurrentUserFavs(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")
        val songs = Server.getCurrentUserFavs()
        println(songs.size)
        assert(true)
    }

    @Test
    fun getTestUserFavs(){
        Server.url = "http://unnamed-chart-server.com:8000"
        Server.logIn("creator", "test1234")
        val songs = Server.getUserFavs("1")
        println(songs.size)
        assert(true)
    }

    @Test
    fun createUser(){
        Server.url = "http://unnamed-chart-server.com:8000"
        val user = Server.createUser("test1", BCrypt.hashpw("test", BCrypt.gensalt(10)))
        println(user)
    }
}