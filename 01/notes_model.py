import csv
from datetime import datetime
from pathlib import Path
from notes_view import *

def print_all_notes(notes: list):
    if len(notes) == 0:
        print("Заметок нет")
    else:
        for num, note in enumerate(notes, 1):
            date = note["date"]
            title = note["name"]
            text = note["text"]
            print(f"{num}. [{date}] {title}: {text}")


def add_note(notes: list):
    '''
    добавлет заметки по одной
    '''
    date_created = datetime.today().date()
    name = input("Введте название заметки: ")
    body = input("Введите текст заметки: ")
    temp = {}
    temp["date"] = str(date_created)
    temp["name"] = name
    temp["text"] = body
    notes.append(temp)


def edit_note(notes: list):
    '''
    Позволяет редактировать текст заметки
    '''
    search_id = search_menu()
    found = []
    if search_id == 1:
        found = find_note_by_name(notes)
    if search_id == 2:
        found = find_note_by_date(notes)
    else:
        print("Неверно заданы критерии поиска")
    if len(found) == 0:
        print("Не найдено ни одной заметки для редактирования")
    else:
        print("Найдены следующие заметки для редактирования")
        for num, line in enumerate(found, 1):
            print(num, line)
        num_to_edit = int(input("Введите номер заметки для редактирования: ")) - 1
        text = input("Введите новый текст заметки: ")
        found[num_to_edit]["text"] = text


def find_note_by_name(notes: list) -> list:
    '''
    Находит все заметки, имеющие заданное название
    '''
    name = input("Введите название заметки: ")
    found = []
    for note in notes:
        if note["name"] == name:
            found.append(note)
    return found


def find_note_by_date(notes: list) -> list:
    '''
    Находит все заметки, созданные в определенную дату
    '''
    date = input("Введите дату создания заметки в формате гггг-мм-дд: ")
    found = []
    for note in notes:
        if note["date"] == date:
            found.append(note)
    return found


def delete_note(notes: list):
    '''
    Удаляет заметки по одной
    '''
    search_id = search_menu()
    if search_id == 1:
        found = find_note_by_name(notes)
    if search_id == 2:
        found = find_note_by_date(notes)
    else:
        print("Неверно заданы критерии поиска")
    if len(found) == 0:
        print("Не найдено ни одной заметки для удаления")
    else:
        print("Найдены следующие заметки для удаления")
        for num, line in enumerate(found, 1):
            print(num, line)
        num_to_delete = int(input("Введите номер заметки для удаления: ")) - 1
        notes.remove(found[num_to_delete])



def save_notes(notes: list):
    with open(Path.cwd() / 'notes.csv', 'w', newline='', encoding='utf-8') as fout:
        csv_writer = csv.writer(fout)
        for note in notes:
            csv_writer.writerow(note.values())


def read_notes() -> list:
    notes = []
    try:
        with open(Path.cwd() / 'notes.csv', 'r', encoding='utf-8') as fin:
            csv_reader = csv.reader(fin)
            for row in csv_reader:
                temp = {}
                temp["date"] = row[0]
                temp["name"] = row[1]
                temp["text"] = row[2]
                notes.append(temp)
    except FileNotFoundError:
        pass
    return notes


