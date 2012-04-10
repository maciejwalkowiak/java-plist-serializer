package pl.maciejwalkowiak.plist;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.maciejwalkowiak.plist.handler.*;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class BasicObjectSerializerTest {
	@InjectMocks
	private BasicObjectSerializer basicObjectSerializer;

	@Mock
	private HandlerWrapper handlerWrapper;

	@Test
	public void testSerializationWhenHandlerFound() throws HandlerNotFoundException {
		//given
		String object = "hello";
		Handler handler = new StringHandler();

		given(handlerWrapper.getHandlerForObject(eq(object))).willReturn(handler);

		//when
		StringBuilder output = basicObjectSerializer.serializeBasicObject(object);

		//then
		assertThat(output.toString()).isEqualTo(handler.handle(object));
	}

	@Test
	public void testThrowingExceptionWhenHandlerNotFound() throws HandlerNotFoundException {
		//given
		String object = "hello";
		given(handlerWrapper.getHandlerForObject(eq(object))).willThrow(new HandlerNotFoundException("handler not found"));

		//when
		try {
			basicObjectSerializer.serializeBasicObject(object);

			//then
			fail();
		} catch(PlistSerializationException e) {

		}
	}


}

