package com.musok.musokdbbrowser.api.exceptions

open class ServerAPIException: Exception()
class IncorrectLoginException: ServerAPIException()
class UserAlreadyRegisteredException: ServerAPIException()
class UnknownException: ServerAPIException()
class InternalServerErrorException: ServerAPIException()
class AuthFailedException: ServerAPIException()
class IncorrectMediaTypeException: ServerAPIException()