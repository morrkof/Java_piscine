package day00.ex04;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();

        int [][] symbols = new int[999][999];
        int offset = 0;
        // пройтись по строке и занести всё в символы
        // проверяем наличие символа перебором первого подмассива до оффсета
        // если не найдено, то добавляем на позицию оффсета,
        // прописываем ++ во втором подмассиве и сдвигаем оффсет
        // если найдено, то просто ++ во втором

        // когда заполним массив, нужно его отсортировать.
        // сортируем по первому подмассиву, но сдвигаем оба
        // тут же проверять если одинаковые, то алфавитный порядок

        // теперь отрисовка: смотрим на первые 10 индексов
        // если больше 10 рисуем решетку если меньше рисуем пробел
        // если больше 9 рисуем решетку если меньше рисуем пробел
        // а ещё тут скейлить

        // придумать как нарисовать остальное

        // program will count character occurences in text
        // show 10 most popular in a chart
        // if 2 characters with equals count, show it in alphab.order
        // chart scalable. max is 10, min 0
        // input is a String with \n at the end
        // max number of chars is 999
        // without multiple iterations
    }
}
