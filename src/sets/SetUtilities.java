package sets;

import java.util.HashSet;
import java.util.Set;

public class SetUtilities
{
	/**
	 * Returns a new set representing the union of s and t.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return a new set representing the union of s and t
	 */
	public static <E> Set<E> union(Set<E> s, Set<E> t)
	{
		Set<E> u = new HashSet<E>(s);
		for (E a : t)
		{
			if (u.contains(a) == false)
			{
				u.add(a);
			}
		}

		return u;
	}

	/**
	 * Returns a new set representing the intersection of s and t.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return a new set representing the intersection of s and t
	 */
	public static <E> Set<E> intersection(Set<E> s, Set<E> t)
	{
		Set<E> i = new HashSet<E>();
		for (E a : t)
		{
			if (s.contains(a))
			{
				i.add(a);
			}
		}
		return i;
	}

	/**
	 * Returns a new set representing the set difference s and t, that is, s \ t.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return a new set representing the difference of s and t
	 */
	public static <E> Set<E> setDifference(Set<E> s, Set<E> t)
	{
		Set<E> d = new HashSet<E>(s);
		d.removeAll(t);
		return d;
	}

	/**
	 * Returns the Jaccard index of the two sets s and t.
	 * 
	 * It is defined as 1 if both sets are empty.
	 *
	 * Otherwise, it is defined as the size of the intersection of the sets, divided
	 * by the size of the union of the sets.
	 * 
	 * Does not modify s or t.
	 * 
	 * @param s
	 * @param t
	 * @return the Jaccard index of s and t
	 */
	public static <E> double jaccardIndex(Set<E> s, Set<E> t)
	{
		if (s.isEmpty() && t.isEmpty())
		{
			return 1;
		}

		else
		{
			double i = intersection(s, t).size();
			double u = union(s, t).size();
			double jac = i / u;
			return jac;
		}
	}
}
