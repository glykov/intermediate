def show_menu() -> int:
    print("1. Показать все заметки")
    print("2. Добавить заметку")
    print("3. Редактировать заметку")
    print("4. Найти заметку по названию")
    print("5. Найти заметки по дате")
    print("6. Удалить заметку")
    print("7. Выйти из программы")
    user_choice = int(input())
    return  user_choice


def search_menu() -> int:
    print("Чтобы найти заметку по названию введите 1")
    print("Чтобы найти заметку по дате создания введите 2")
    search_id = int(input())
    return search_id