package goit.module14.controller;


import goit.module14.note.Note;
import goit.module14.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/note")

public class NoteController {

    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/create")
    public String createModelNote(Model model){

        model.addAttribute("note", new Note());
        return "new_note";
    }
    @PostMapping("/create")
    public String saveNote(@ModelAttribute Note note) {
        noteService.add(note);
        return "redirect:/note/list";
    }

    @GetMapping("/edit")
    public String UpdateNote(@RequestParam("id") long id, Model model) {

        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return "update_note";
    }
    @PostMapping("/edit")
    public String SaveNote(@ModelAttribute Note note) {
        noteService.update(note);
        return "redirect:/note/list";
    }

    @GetMapping("/list")
    public String viewAllNote(Model model) {

        model.addAttribute("alllist", noteService.listAll());
        return "page";
    }
    @PostMapping("/delete")
    public String deleteNote(@RequestParam("id")  long id) {

        noteService.deleteById(id);
        return "redirect:/note/list";
    }

}
