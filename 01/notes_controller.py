from notes_view import *
from notes_model import *

def run_work():
    notes = read_notes()
    
    while True:
        user_choice = show_menu()
        if user_choice == 1:
            print_all_notes(notes)
        elif user_choice == 2:
            add_note(notes)
        elif user_choice == 3:
            edit_note(notes)
        elif user_choice == 4:
            found = find_note_by_name(notes)
            print("Найдены слкдующие заметки:")
            print_all_notes(found)
        elif user_choice == 5:
            found = find_note_by_date(notes)
            print("Найдены слкдующие заметки:")
            print_all_notes(found)
        elif user_choice == 6:
            delete_note(notes)
        elif user_choice == 7:
            save_notes(notes)
            break
        else:
            print("Введен неверный пункт меню. Попробуйте снова")
    
    save_notes(notes)

    