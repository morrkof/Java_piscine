package edu.school21.classes;

import java.util.Random;

public class Pony {
    private String name;
    private Integer numberOfWings;
    private Boolean isHorny;

    public Boolean Fly(int distance, String direction) {
        Random random = new Random();
        if (numberOfWings > 0 && random.nextInt(100) % 2 == 0) {
            System.out.println(name + " is flying in the direction of the " + direction + " " + distance + " meters.");
            return Boolean.TRUE;
        } else if (numberOfWings <= 0) {
            System.out.println(name + " has no wings :C");
            return Boolean.FALSE;
        } else {
            System.out.println(name + "doesn't want to fly in this weather! ⛈️");
            return Boolean.FALSE;
        }
    }

    public String doMagick(Integer power) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < power; i++) {
            stringBuilder.append("･*:.｡.*･ﾟ･*☆✨");
        }
        return stringBuilder.toString();
    }

    public Pony() {
        this.name = "Pony";
        this.numberOfWings = 0;
        this.isHorny = false;
    }

    public Pony(String name, Integer numberOfWings, Boolean isHorny) {
        this.name = name;
        this.numberOfWings = numberOfWings;
        this.isHorny = isHorny;
    }

    @Override
    public String toString() {
        return "\uD83E\uDD84 Pony < " +
                "name='" + name + '\'' +
                ", numberOfWings=" + numberOfWings +
                ", isHorny=" + isHorny +
                " >";
    }
}
