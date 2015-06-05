package com.kani

import java.io.{FileOutputStream, InputStream}

import com.sun.net.httpserver.{Headers, HttpExchange, HttpHandler}

class FakeRawRequestHandler extends HttpHandler {
  def handle(exchg: HttpExchange): Unit = {
    val userHome = System.getProperty("user.home")
    val requestHeaders: Headers = exchg.getRequestHeaders
    val requestInputStream: InputStream = exchg.getRequestBody
    val requestBody = convertStreamtoByteArray(requestInputStream)

    val reqHeadersKeySet = requestHeaders.keySet()
    val reqHeadersKeyItr: java.util.Iterator[String] = reqHeadersKeySet.iterator()

    //exchg.getResponseHeaders.add("Content-Type", "text/html; charset=UTF-8")

    val response = new StringBuilder("Dummy response with request headers\n");
    response.append("httpbody:").append("[\n").append(new String(requestBody)).append("\n]")
    response.append("\n")
    while (reqHeadersKeyItr.hasNext) {
      val requestHeaderKey = reqHeadersKeyItr.next()
      val requestHeaderVal = requestHeaders.get(requestHeaderKey)
      response.append(requestHeaderKey).append(":").append(requestHeaderVal).append("\n")
    }

    exchg.sendResponseHeaders(200, response.toString.getBytes.length)

    val responseOs = exchg.getResponseBody
    responseOs.write(response.toString.getBytes)
    println(response.toString)
    val timestamp = System.currentTimeMillis()
    val outs = new FileOutputStream(userHome +"/"+ timestamp);
    val outsPayload = new FileOutputStream(userHome+"/peppol_payload_" + timestamp);
    outs.write(response.toString.getBytes)
    outsPayload.write(requestBody)
    outs.close()
    outsPayload.close()
    responseOs.close()
  }

  private def convertStreamtoString(stream: InputStream): String = {
    try {
      new String(Stream.continually(stream.read).takeWhile(-1 !=).map(_.toByte).toArray)
    } catch {
      case e: Exception => e.printStackTrace
        "Error when converting body to string!"
    }
  }

  private def convertStreamtoByteArray(stream: InputStream) = {
    try {
      Stream.continually(stream.read).takeWhile(-1 !=).map(_.toByte).toArray
    } catch {
      case e: Exception => e.printStackTrace
        "Error when converting body to string!".getBytes
    }
  }
}
