package pl.maciejwalkowiak.plist.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import pl.maciejwalkowiak.plist.PlistSerializerImpl;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class CollectionHandlerTest {
	@InjectMocks
	private CollectionHandler collectionHandler;

	@Mock
	private PlistSerializerImpl plistSerializer;

	@Test
	public void testSupportsArray() throws Exception {
		//given
		int[] array = new int[]{1, 2, 3};

		//when
		boolean supports = collectionHandler.supports(array);

		//then
		assertThat(supports).isTrue();
	}

	@Test
	public void testSupportsList() throws Exception {
		//given
		List<Integer> list = Arrays.asList(1, 2, 3);

		//when
		boolean supports = collectionHandler.supports(list);

		//then
		assertThat(supports).isTrue();
	}

	@Test
	public void testIntConversion() {
		//given
		List<Integer> list = Arrays.asList(1, 2, 3);

		given(plistSerializer.serialize(anyInt())).willReturn("<integer>1</integer>");

		//when
		String result = collectionHandler.handle(list);

		//then

		assertThat(result).isEqualTo("<array><integer>1</integer><integer>1</integer><integer>1</integer></array>");

		for (Integer i : list) {
			Mockito.verify(plistSerializer).serialize(eq(i));
		}
	}
}
