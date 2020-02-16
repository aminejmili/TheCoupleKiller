package com.project.thecouplekiller;

public class Room {
    Player playerOne;
    Player pPlayerTwo;
    int roomId;

    public Room(Player playerOne, Player pPlayerTwo, int roomId) {
        this.playerOne = playerOne;
        this.pPlayerTwo = pPlayerTwo;
        this.roomId = roomId;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getpPlayerTwo() {
        return pPlayerTwo;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public void setPlayerTwo(Player pPlayerTwo) {
        this.pPlayerTwo = pPlayerTwo;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Room{" +
                "playerOne=" + playerOne.toString() +
                ", pPlayerTwo=" + pPlayerTwo.toString() +
                ", roomId=" + roomId +
                '}';
    }

    public Room() {
    }


    public Room(Player playerOne) {
        this.playerOne = playerOne;
    }
}
