package com.github.kharigardner.chatroom4s.server

import java.net._
import java.io._
// import logger
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

object ChatRoomServer {
    val logger: Logger = LogManager.getLogger(this.getClass)

    def main(args: Array[String]): Unit = {
        logger.info("Starting ChatRoomServer...")
        var port: Int = 8080
        val serverSocket = new ServerSocket(port)
        logger.info(s"Listening on port $port")

        while (true) {
            var clientsocket: Socket = serverSocket.accept()
            
            clientsocket.sendUrgentData("Welcome to the chatroom!".getBytes)

            logger.info(s"Accepted connection from ${clientsocket.getInetAddress}")
            println(s"Accepted connection from ${clientsocket.getInetAddress}")

            val clientThread = new Thread(new ClientHandler(clientsocket))
            clientThread.start()
        }
    }
}

class ClientHandler(clientSocket: Socket) extends Runnable {
    override def run(): Unit = {
        try {
            val in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream))
            val out = new PrintWriter(clientSocket.getOutputStream, true)

            // read incoming messages
            var inputLine: String = in.readLine()
            while (inputLine != null) {
                logger.info(s"Received message from ${clientSocket.getInetAddress}: $inputLine")
                println(s"Received message from ${clientSocket.getInetAddress}: $inputLine")
            }

            // process the message and send a response back to the client
            val response = processMessage(inputLine)
            out.println(response)

            inputLine = in.readLine()
        }
        catch {
            case e: IOException =>
                logger.error(e.getMessage)
                e.printStackTrace()
        } finally {
            clientSocket.close()
            logger.info(s"Closed connection from ${clientSocket.getInetAddress}")
            println(s"Closed connection from ${clientSocket.getInetAddress}")
        }
    }

    def processMessage(message: String): String = {
        "Response" + message
    }

}