package com.kani

import java.net.InetSocketAddress

import com.sun.net.httpserver.HttpServer

/**
 * Created by kanishka on 5/7/15.
 */
object RawHttpServer extends App{
  val fakeRowRequestContextUrl = "/as2dumpserver/endpoint"
  startServer()

  private def startServer() {
    val port = 21000
    val inetSockAddr: InetSocketAddress = new InetSocketAddress(port)
    val fakeHttpServer = HttpServer.create(inetSockAddr, port)
    fakeHttpServer.createContext(fakeRowRequestContextUrl, new FakeRawRequestHandler)
    fakeHttpServer.setExecutor(null)
    println("Endpoint :" + "http://localhost:"+port+fakeRowRequestContextUrl)
    fakeHttpServer.start()
  }
}
