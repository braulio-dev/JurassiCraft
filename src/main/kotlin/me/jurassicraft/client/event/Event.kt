package me.jurassicraft.client.event

abstract class Event

class CancelableEvent : Event() {
    var canceled = false
}