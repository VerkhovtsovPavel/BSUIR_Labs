package �omparator;

import java.util.Comparator;

import notepad.Note;

public class NoteDateComparator implements Comparator<Note> {

	@Override
	public int compare(Note o1, Note o2) {
		return o1.createDate.compareTo(o2.createDate);
	}
}
