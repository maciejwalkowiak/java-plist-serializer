package pl.maciejwalkowiak.plist.handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import pl.maciejwalkowiak.plist.PlistSerializerImpl;
import pl.maciejwalkowiak.plist.strategy.DefaultNamingStrategy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class MapHandlerTest {
	@InjectMocks
	private MapHandler mapHandler;

	@Mock
	private PlistSerializerImpl plistSerializer;

	@Test
	public void testMapSerialization() {
		Map<String, Integer> map = new LinkedHashMap<String, Integer>();
		map.put("key1", 1);
		map.put("key2", 2);

		given(plistSerializer.serialize(anyInt())).willReturn("<integer>1</integer>");
		given(plistSerializer.getNamingStrategy()).willReturn(new DefaultNamingStrategy());

		String xml = mapHandler.doHandle(map);

		assertThat(xml).isEqualTo("<dict><key>key1</key><integer>1</integer><key>key2</key><integer>1</integer></dict>");
	}

	@Test
	public void testWhenPlistSerializerNotSet() {
		//given
		MapHandler mapHandler = new MapHandler(null);

		//when
		try {
			mapHandler.handle(new HashMap<String, String>());

			//then
			fail();
		} catch (IllegalStateException e) {

		}
	}
}
