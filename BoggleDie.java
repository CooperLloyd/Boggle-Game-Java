import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
/**
 * A class that represents an 6-sided die where the sides are decorated with a
 * string.
 */
public class BoggleDie {
	private String[] faces;
	private String currentFace;

	// add your fields here
	

	/**
	 * Initializes an n-sided die where the sides are decorated with the strings in
	 * the specified array. Each string in the array is assigned to exactly one face
	 * of the die. The die will have as many faces as there are strings in the
	 * array. For example:
	 * 
	 * <pre>
	 * String[] faces = { "A", "A", "E", "E", "G", "N" };
	 * BoggleDie d = new BoggleDie(faces);
	 * </pre>
	 * 
	 * <p>
	 * would construct a 6-sided die where the sides are lettered {@code "A"},
	 * {@code "A"}, {@code "E"}, {@code "E"}, {@code "G"}, and
	 * {@code "N"}.
	 * 
	 * <p>
	 * The mapping of the letters to the die faces is unspecified; all that is
	 * guaranteed is that each letter in the given string is mapped to one and only
	 * one face of the die.
	 * 
	 * <p>
	 * The current value of the die is unspecified; calling {@code getValue()}
	 * immediately after constructing a die could return any face that belongs to
	 * the die.
	 * 
	 * @param faces
	 *            an array of strings, one string for each face of the die
	 * 
	 * @throws IllegalArgumentException
	 *             if faces.length == 0
	 * 
	 */
	public BoggleDie(String[] faces) {
		this.faces = faces;
	}

	/**
	 * Construct an independent copy of an existing die. The new die will have the
	 * same strings on the same faces as the existing die.
	 * 
	 * <p>
	 * The current value of this die will be the same as the other die.
	 * 
	 * @param other
	 *            the die to copy
	 */
	public BoggleDie(BoggleDie other) {
		this.faces = other.faces;
		this.currentFace = other.currentFace;
	}

	/**
	 * Rolls the die to a new random face, and returns the string on the face.
	 * 
	 * @return the string on face after rolling the die
	 */
	public String roll() {
		int side = (int)(Math.random()*6);
		this.currentFace = this.faces[side];
		return this.faces[side];
	}

	/**
	 * Returns the string corresponding to the current face value of the die.
	 * 
	 * @return the string corresponding to the current face value of the die
	 */
	public String currentFace() {
		return this.currentFace;
	}


	/**
	 * Returns a hash code for this die. 
	 * 
	 * @return a hash code for this die
	 */
	@Override
	public int hashCode() {
		int result = this.currentFace.hashCode();
		int c = this.faces.hashCode();
		result = 31 * result + c;
		return result;
	}

	/**
	 * Compares this die to the specified object. The result is {@code true} if
	 * and only if all of the following are {@code true}:
	 * 
	 * <ul>
	 * <li>the argument is not {@code null}</li>
	 * <li>the argument is a {@code BoggleDie} reference</li>
	 * <li>the current face values of this die and the
	 * other die are {@code equals}</li>
	 * </ul>
	 * 
	 * <p>
	 * Note that two dice can be equal if they have at least one face label
	 * in common (i.e., both dice do not need to have an identical labelling
	 * of faces.
	 * 
	 * @param obj
	 *            the object to compare
	 * @return true if the two dice are equal (see above), and
	 *         false otherwise
	 */
	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof BoggleDie)) { // of same type
			return false;
		}
		BoggleDie other = (BoggleDie) obj;
		for (String o : other.faces) { // check for equal face label
			for (String t : this.faces) {
				if (t.equals(o)) {
					return true;
				}
			}
		}
		if (this.currentFace.equals(other.currentFace)) { // check current face for equality
			return true;
		}
		return false;
	}

	/**
	 * Returns a string representation of this die. The string are the strings of
	 * the faces separated by a comma and space. The strings appear in order of
	 * their numeric mapping. For example, the die with faces:
	 * 
	 * <p>
	 * {@code 1, 2, 3, 4, 5, 6}
	 * 
	 * <p>
	 * having face strings:
	 * 
	 * <p>
	 * {@code "C", "M", "I", "QU", "U", "T"}
	 * 
	 * <p>
	 * has the string representation {@code "C, M, I, QU, U, T"}.
	 * 
	 * 
	 * @return a string representation of this die
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder bld = new StringBuilder();
		for(String s : this.faces){
			 bld.append(" ").append(s).append(",");
		}
		return bld.toString();
	}
	public static void main(String[] args) {
		String[] faces = { "A", "B", "C", "E", "D", "N" };
	 	BoggleDie d = new BoggleDie(faces);
	 	System.out.println(d.roll());

	}

}