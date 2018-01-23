Hibernate/JPA discussion:
------------------------

mysql:
-------
mysql -u root -p


Derby:
--------
1. from one cmd run: startNetworkServer
2. from other cmd run ij command and create database with username and password:
connect 'jdbc:derby://localhost:1527/demodb;create=true;user=root;password=root';



condider:POJO:
------
public class Book {
	private int id;
	private String isbn;
	private String title;
	private String author;
	private double price;
}



Apply annotations:
---------------------


@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String isbn;
	private String title;
	private String author;
	private double price;
}

persistance.xml
---------------

<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
	<persistence-unit name="jpa" transaction-type="RESOURCE_LOCAL">
		<class>com.demo.Book</class>
		<properties>
			<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
			<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/foo"/>
			<property name="javax.persistence.jdbc.user" value="root"/>
			<property name="javax.persistence.jdbc.password" value="root"/>
			<property name="hibernate.hbm2ddl.auto" value="create"/>
			<property name = "hibernate.show_sql" value = "true" />
		</properties>
	</persistence-unit>
</persistence>





Main: saving
--------
EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");

		EntityManager em = emf.createEntityManager();

		Book book=new Book("123A", "head first", "katty", 370.5);

		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			em.persist(book);

			tx.commit();
			System.out.println("book is saved...");
		} catch (PersistenceException ex) {
			if (tx != null)
				tx.rollback();
		}

		em.close();
		emf.close();




finding a book
------------
	Book book=em.find(Book.class, 1);

updating:
----------
		//code to update a book
		em.getTransaction().begin();
		
		Book book=em.find(Book.class, 1);
		book.setAuthor("amit");
		em.merge(book);
		em.getTransaction().commit();
		
		em.close();
		emf.close();



geetting list of all books:
------------------------
	
		List<Book> allBooks=em.createQuery("from Book").getResultList();
		for(Book b: allBooks)
			System.out.println(b.getTitle());


deleting:
-----------

		em.getTransaction().begin();
		
		Book book=em.find(Book.class, 1);
		em.remove(book);
		em.getTransaction().commit();





CURD application:
---------------------

	public interface BookDao {
		public List<Book> getAllBooks();
		public Book getBookById(int bookId);
		public Book addBook(Book book);
		public Book updateBook(Book book);
		public Book removeBook(int bookId);
	}


	
	
public class BookDaoImp implements BookDao {

	private EntityManagerFactory emf;
	
	
	public BookDaoImp(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public List<Book> getAllBooks() {
		EntityManager em=emf.createEntityManager();
		List<Book> allBooks=em.createQuery("from Book").getResultList();
		return allBooks;
	}

	@Override
	public Book getBookById(int bookId) {
		EntityManager em=emf.createEntityManager();
		Book book=em.find(Book.class, bookId);
		return book;
	}

	@Override
	public Book addBook(Book book) {
		EntityManager em=emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(book);
		em.flush();
		em.getTransaction().commit();
		em.close();
		return book;
	}

	@Override
	public Book updateBook(Book book) {
		return null;
	}

	@Override
	public Book removeBook(int bookId) {
		return null;
	}

}





Spring JPA integration:
------------------------



Problem:
---------

	Each DAO method must:
	------------------------------
	
	1. Obtain a EntityManager instance
	2. Start a transaction
	3. Perform the persistence operation
	4. commit the transaction.
	5. Each DAO method should include its own duplicated 
		exception-handling implementation. 



	These are exactly the problems that motivate
	 us to use Spring with Hibernate


	-------------------------
	 "template design patten"
	-------------------------


	Template Pattern: 
	----------------
		To clean the code and provide more manageable code, 
		Spring utilizes a pattern called Template Pattern. 

		By this pattern, a template object wraps all of the boilerplate
		 repetitive code. 

		Then, this object delegates the persistence calls
		 as a part of functionality in the template. 

		In the Hibernate case, HibernateTemplate extracts all 
		of the boilerplate code, such as obtaining a Session, 
		performing transaction, and handing exceptions.

	

	Data tier implementation with Spring
	---------------------------------------

		we don't  need to implement code for 
		obtaining Session objects, starting and committing transactions,
		 and handling Hibernate exceptions. 

		(We use a HibernateTemplate instance to delegate
		 persistence calls to Hibernate, without direct interaction 
		with Hibernate)



	What we gains with Spring
	===========================================

	1. HibernateTemplate/JpaTemplate object removes the boilerplate code 

	2. Invocation of one of HibernateTemplate's methods throws
	 the generic DataAccessException exception instead of 	HibernateException

	3. Spring lets us demarcate transactions declaratively, 
	 instead of implementing duplicated transaction-management code.


	---------------------------------------
	NOTE:Spring-Hibernate best practices
	------------------------------------------

	Don't use HibernateDaoSupport/HibernateTemplate/JpaTemplate
	-------------------------------------------------------

		Why?
		------
		==>it unnecessarily ties your code to Spring classes.

		===>Since Hibernate 3.0.1 you don't need it any more -
		 you can write a code against a plain Hibernate API while using 
		Spring-managed transactions. 

		==>All you need is to configure Spring transaction support,
		 inject SessionFactory and call getCurrentSession() on it
		 when you need to work with session.

		==>Another benefit of HibernateTemplate is exception translation. 
		Without HibernateTemplate the same functionality can be 
		achieved by using @Repository annotation

	-----------------------------------------------------------------------



	CURD application using Spring Hibernate JPA integration
	-------------------------------------------------------

	Main<======> Service layer<========> Persistance layer<=======> DB


	com.model.persistance
	--------------------

	@Entity
	public class Book {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
		private String isbn;
		private String title;
		private String author;
		private double price;


	public interface BookDao {
		public List<Book> getAllBooks();
		public Book getBookById(int bookId);
		public Book addBook(Book book);
		public Book updateBook(Book book);
		public Book removeBook(int bookId);
	}


	
	@Repository
	public class BookDaoImp implements BookDao {
	
		@PersistenceContext
		private EntityManager em;
	
		@Override
		public List<Book> getAllBooks() {
			return em.createQuery("from book").getResultList();
		}
	
		@Override
		public Book getBookById(int bookId) {
			return em.find(Book.class, bookId);
		}
	
		@Override
		public Book addBook(Book book) {
			em.persist(book);
			em.flush();
			return book;
		}
	
		@Override
		public Book updateBook(Book book) {
			return em.merge(book);
		}
	
		@Override
		public Book removeBook(int bookId) {
			Book book = em.find(Book.class, bookId);
			if (book != null)
				em.remove(bookId);
			return book;
		}
	}



	com.model.service
	--------------------
	public interface BookService {
		public List<Book> getAllBooks();
		public Book getBookById(int bookId);
		public Book addBook(Book book);
		public Book updateBook(Book book);
		public Book removeBook(int bookId);
	}


	
	@Service(value="bs")
	@Transactional
	public class BookServiceImp implements BookService {
	
		@Autowired
		private BookDao dao;
	
		@Override
		public List<Book> getAllBooks() {
			return dao.getAllBooks();
		}
	
		@Override
		public Book getBookById(int bookId) {
			return dao.getBookById(bookId);
		}
	
		@Override
		public Book addBook(Book book) {
			return dao.addBook(book);
		}
	
		@Override
		public Book updateBook(Book book) {
			return dao.updateBook(book);
		}
	
		@Override
		public Book removeBook(int bookId) {
			return dao.removeBook(bookId);
		}
	
	}

	main
	-----
		ApplicationContext ctx=new ClassPathXmlApplicationContext("beans.xml");
		
		BookService service=(BookService) ctx.getBean("bs");
		
		Book book=new Book("123A", "head first", "katty", 370.5);

		Book book2=service.addBook(book);
	



	persistance.xml
	---------------



<persistence version="2.0"
	xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
	<persistence-unit name="curd">
		<provider>org.hibernate.ejb.HibernatePersistence</provider>
		<class>com.yms.bookapp.model.persistance.Book</class>
		<class>com.yms.bookapp.model.persistance.Record</class>
		<properties>
			<!-- <property name="hibernate.dialect" value="org.hibernate.dialect.DerbyDialect"/> -->
			<property name="hibernate.hbm2ddl.auto" value="update" />
			<property name="hibernate.show_sql" value="true" />
		</properties>
	</persistence-unit>
</persistence>



for derby database:
----------------------

<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
	<persistence-unit name="bank" transaction-type="RESOURCE_LOCAL">
	<provider>org.hibernate.ejb.HibernatePersistence</provider>
		<class>com.demo.bank.model.persistance.Account</class>
		<properties>
		<property name="hibernate.dialect" value="org.hibernate.dialect.DerbyDialect"/>
			<property name="hibernate.hbm2ddl.auto" value="update" />
			<property name = "hibernate.show_sql" value = "true" />
		</properties>
	</persistence-unit>
</persistence>



beans.xml
-----------
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:tx="http://www.springframework.org/schema/tx"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
		http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd">


	<context:annotation-config/>
	<context:component-scan base-package="com"/>

	<!-- for derby db -->
	<bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">

		<property name="driverClassName" value="org.apache.derby.jdbc.ClientDriver" />
		<property name="url" value="jdbc:derby://localhost:1527/demodb" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>

	<!-- for mysql -->

	<bean id="dataSource"
		class="org.springframework.jdbc.datasource.DriverManagerDataSource">

		<property name="driverClassName" value="com.mysql.jdbc.Driver" />
		<property name="url" value="jdbc:mysql://localhost:3306/foo" />
		<property name="username" value="root" />
		<property name="password" value="root" />
	</bean>

	<bean
		class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" id="entityManagerFactory">
		<property name="persistenceUnitName" value="curd" />
		<property name="dataSource" ref="dataSource" />

	</bean>

	<tx:annotation-driven />

	<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
		<property name="entityManagerFactory" ref="entityManagerFactory" />
	</bean>
</beans>



Note:
--------
 To integrate Spring with JPA, Spring provides three ways to fetch EntityManagerFactory. 

1. LocalEntityManagerFactoryBean 
2.LocalContainerEntityManagerFactoryBean 
3.EntityManagerFactory from JNDI 











Now apply @Loggable cross cutting concern:
-----------------------------------------

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Loggable {

}





import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class MethodLogger {
	private static final Logger logger=LoggerFactory.getLogger(MethodLogger.class); 
	
  @Around("@annotation(Loggable)")
  public Object around(ProceedingJoinPoint point) throws Throwable {
    long start = System.currentTimeMillis();
    Object result = point.proceed();
    logger.info("start "+MethodSignature.class.cast(point.getSignature()).getMethod().getName()+" is called"+" takes " +(System.currentTimeMillis() - start));
    return result;
  }
}















----------------------------------------------------------------------------
	Hibernate code sample
---------------------------------------------------------------------------
hibernate.cfg.xml 

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">
<hibernate-configuration>
	<session-factory>
		<!-- database connection setting -->
		<property name="connection.url">jdbc:mysql://localhost:3306/fooraj121</property>
		<property name="connection.username">root</property>
		<property name="connection.password">root</property>
		<property name="connection.driver_class">com.mysql.jdbc.Driver</property>
		<property name="dialect">org.hibernate.dialect.MySQLDialect</property>

		<!-- disable the second level cache -->
		<property name="cache.provider_class">org.hibernate.cache.NoCacheProvider</property>
		<property name="show_sql">true</property>

		<property name="format_sql">true</property>
		<property name="hbm2ddl.auto">update</property>
		<!-- jdbc connection pool build in  -->
		<property name="connection.pool_size">1</property>
		<!-- <property name="current_session_context_class">thread</property> -->
		<mapping class="com.model5.Customer" />
		
	</session-factory>
</hibernate-configuration>




		SessionFactory factory=new AnnotationConfiguration().configure().buildSessionFactory();
		
		Session session=factory.getCurrentSession();
		
		session.beginTransaction();
		
		Customer customer=new Customer();
		customer.setCustomerId(121);
		customer.setCustomerName("rajiv");
		customer.setCustomerAddess("noida");
		session.save(customer);
		System.out.println("customer saved!");
		session.getTransaction().commit();




http://stackoverflow.com/questions/8621906/is-buildsessionfactory-deprecated-in-hibernate-4

-------------------------------------------------------------------------------------------
	Using derby database
------------------------------------------------------------------------------------------

derby database:
===============================



Install Derby:
---------------------

DERBY_HOME
C:\tools\db-derby-10.12.1.1-bin\db-derby-10.12.1.1-bin

path
C:\tools\db-derby-10.12.1.1-bin\db-derby-10.12.1.1-bin\bin


then try to connect to db:
------------------------------

1. from one cmd run: startnetworkserver

2. from other cmd run ij command and create database with username and password:

connect 'jdbc:derby://localhost:1527/demodb;create=true;user=root;password=root';



Driver: org.apache.derby.jdbc.ClientDriver
conn: jdbc:derby://localhost:1527/demodb;create=true;;user=root;password=root'
-------------------------------

  <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.21</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate.javax.persistence</groupId>
            <artifactId>hibernate-jpa-2.0-api</artifactId>
            <version>1.0.1.Final</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>4.0.1.Final</version>
        </dependency>




<?xml version="1.0" encoding="UTF-8" ?>
<persistence xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
	version="2.0" xmlns="http://java.sun.com/xml/ns/persistence">
	<persistence-unit name="persistenceUnit" transaction-type="RESOURCE_LOCAL">
		<!-- shouldn't be valid for java SE per specification, but it works for EclipseLink ... -->
		<exclude-unlisted-classes>false</exclude-unlisted-classes>
		<properties>
			<property name="javax.persistence.jdbc.driver" value="org.apache.derby.jdbc.ClientDriver" />
			<property name="javax.persistence.jdbc.url" value="jdbc:derby://localhost:1527/demodb" />
			<property name="javax.persistence.jdbc.user" value="root" />
			<property name="javax.persistence.jdbc.password" value="root" />

			<!-- EclipseLink should create the database schema automatically -->
			<property name="eclipselink.ddl-generation" value="create-tables" />
			<property name="eclipselink.ddl-generation.output-mode" value="database" />
			<property name="eclipselink.logging.level" value="FINE"/>			
		</properties>

	</persistence-unit>
</persistence>


