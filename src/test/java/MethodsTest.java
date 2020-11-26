import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runner.manipulation.Ordering;
import org.junit.runners.Parameterized;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.quality.Strictness;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class MethodsTest {

    @Mock
    Database databaseMock;
    @Mock
    Server serverMock;
    @Mock
    Server serverMock2;

    @Rule public MockitoRule mockitoRule = MockitoJUnit.rule();

    List<Integer> ints;
    @Before
    public void setUp() throws Exception {
        when(databaseMock.get(1)).thenReturn("test");
        when(databaseMock.get(10)).thenReturn("test");
        when(databaseMock.put(anyString(), anyInt())).thenAnswer(returnsFirstArg());
        when(serverMock.doSomething()).thenReturn(10);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOfsorteerOplopendJuistSorteert(){
        ints = new ArrayList<Integer>(Arrays.asList(1, 5, 8, 4, 2, 1, 5));
        Methods m = new Methods();
        m.sorteerOplopend(ints);
        assertEquals(ints, new ArrayList<Integer>(Arrays.asList(1, 1, 2, 4, 5, 5, 8)));
    }

    @Test
    public void testOfsorteerAflopendJuistSorteert(){
        ints = new ArrayList<Integer>(Arrays.asList(1, 5, 8, 4, 2, 1, 5));
        Methods m = new Methods();
        m.SorteerAflopend(ints);
        assertEquals(ints, new ArrayList<Integer>(Arrays.asList(8, 5, 5, 4, 2, 1, 1)));
    }

    @Parameterized.Parameter(0)
    public List<Integer> l1;
    @Parameterized.Parameter(1)
    public List<Integer> result;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { {Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)},
                                           {Arrays.asList(3, 2, 1), Arrays.asList(1, 2, 3)},
                                           {Arrays.asList(5, 5, 8, 1, 2, 4, 1), Arrays.asList(1, 1, 2, 4, 5, 5, 8)} };
        return Arrays.asList(data);
    }

    @Test
    public void testOplopendGeparametiseerd(){
        ints = l1;
        Methods m = new Methods();
        m.sorteerOplopend(ints);
        assertEquals(result, ints);
    }

    @Test
    public void VerwachtExceptionAlsArgumentnullIs(){
        Methods m = new Methods();
        assertThrows(NullPointerException.class, () -> m.SorteerAflopend(null));
    }

    @Test
    public void testToUpperGet(){
        Methods m = new Methods(databaseMock, serverMock);
        String result = m.getUppercaseFromDatabase(1);
        assertEquals("TEST", result);
        verify(databaseMock).get(1);
    }

    @Test
    public void testToUpperGetfail(){
        Methods m = new Methods(databaseMock, serverMock);
        assertThrows(NullPointerException.class, () -> m.getUppercaseFromDatabase(2));
    }

    @Test
    public void testDatabaseSpy(){
        Database database2 = new Database();
        Database databaseSpy = spy(database2);
        doReturn("foo").when(databaseSpy).get(1);
        Methods m = new Methods(databaseSpy, serverMock);
        String result1 = m.getUppercaseFromDatabase(0);
        String result2 = m.getUppercaseFromDatabase(1);
        assertEquals("0", result1);
        assertEquals("FOO", result2);
    }

    @Test
    public void testMethodCalls(){
        Methods m = new Methods(databaseMock, serverMock);
        String s1 = m.getUppercaseFromDatabase(1);
        String s2 = m.getUppercaseFromDatabase(10);
        int i = m.getFromServer();
        int i2 = m.getFromServer();
        assertEquals(s1, s2);
        verify(databaseMock).get(ArgumentMatchers.eq(1));
        verify(databaseMock).get(ArgumentMatchers.eq(10));
        verify(serverMock, times(2)).doSomething();
        verifyNoMoreInteractions(databaseMock);
        verifyNoMoreInteractions(serverMock);
    }

    @Test
    public void failtest(){
        assertTrue(false);
    }

}