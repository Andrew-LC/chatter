package com.example.routes

import com.example.models.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.userRouting() {
    route("/user"){
	get {
	    call.respond(userStorage)
	}
        get("{usernname?}") {
	    val username = call.parameters["name"]

	    val user =
                userStorage.find { it.username == username } ?: return@get call.respondText(
                    "No customer: $username",
                    status = HttpStatusCode.NotFound
                )
            call.respond(user)
	}
	post {
	    var user = call.receive<User>()
	    userStorage.add(user)
	    call.respondText("User stored correctly", status = HttpStatusCode.Created)
	}
	
    } 
}


