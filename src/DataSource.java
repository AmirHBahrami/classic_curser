public interface DataSource<T>{
	public T get();
	public void end(); // unnecessary, only to run if the end ever pops up
}
