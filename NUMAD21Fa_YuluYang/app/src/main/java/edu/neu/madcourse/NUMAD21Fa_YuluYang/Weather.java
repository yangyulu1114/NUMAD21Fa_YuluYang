package edu.neu.madcourse.NUMAD21Fa_YuluYang;

public class Weather {
    long time;
    int minTemp;
    int maxTemp;
    String weather;
    String des;

    public Weather(long time, int minTemp, int maxTemp, String weather, String des) {
        this.time = time;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.weather = weather;
        this.des = des;
    }

    public long getTime() {
        return time;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }

    public String getWeather() {
        return weather;
    }

    public String getDes() {
        return des;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "time=" + time +
                ", minTemp=" + minTemp +
                ", maxTemp=" + maxTemp +
                ", weather='" + weather + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
