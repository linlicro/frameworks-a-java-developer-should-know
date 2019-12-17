package me.icro.java.spring.icrospring.ioc;

import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-10 2:41 PM
 */
public class BeanFactory {
    private String location;
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<String, Object>(64);
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    private final Object startupShutdownMonitor = new Object();

    public BeanFactory(String location) {
        setConfigLocation(location);

        try {
            loadBeans(this.location);
        } catch (Exception e) {
            // ignore
        }

        refresh();
    }

    public void registerBeanDefintion(String beanName, BeanDefinition definition) {
        beanDefinitionMap.put(beanName, definition);
    }

    public Object getBean(String beanName) {
        Object bean = this.singletonObjects.get(beanName);
        if (null == bean) {
            try {
                bean = doCreateBean(beanName, beanDefinitionMap.get(beanName));
            } catch (Exception e) {
                // ignore
            }
        }
        return bean;
    }

    private void refresh() {
        synchronized (this.startupShutdownMonitor) {
            beanDefinitionMap.forEach((id, beanDefinition) -> {
                try {
                    Object bean = beanDefinition.getBeanClass().newInstance();
                    for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValueList()) {
                        Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                        declaredField.setAccessible(true);
                        declaredField.set(bean, propertyValue.getValue());
                    }
                    singletonObjects.put(beanDefinition.getBeanClassName(), bean);
                } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
                    // ignore
                }
            });
        }
    }

    private void setConfigLocation(String location) {
        Assert.notNull(location, "Config locations must not be null.");
        this.location = location;
    }

    private Object doCreateBean(final String beanName, final BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        Object object = createBeanInstance(beanDefinition);
        applyPropertyValues(object, beanDefinition);
        return object;
    }

    private Object createBeanInstance(final BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }

    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        if (null == propertyValues) {
            return;
        }
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            field.set(bean, propertyValue.getValue());
        }
    }

    private void loadBeans(String location) throws Exception {

        /*
            <beans>
                <bean id="simpleBean" class="me.icro.java.spring.ch01_basic.SimpleBean">
                    <property name="message" value="message" />
                </bean>
            </beans>
         */

        // load an xml file, convert to BeanDefinition.
        InputStream inputStream = new FileInputStream(location);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        Element root = document.getDocumentElement();
        NodeList beanNodeList = root.getChildNodes();

        for (int i = 0; i < beanNodeList.getLength(); i++) {
            Node node = beanNodeList.item(i);
            if (node instanceof Element) {
                Element element = (Element) node;
                String id = element.getAttribute("id");
                String className = element.getAttribute("class");

                NodeList propertiesNodeList = element.getElementsByTagName("property");
                List<PropertyValue> propertyValueList = new ArrayList<PropertyValue>();
                for (int pIndex = 0; pIndex < propertiesNodeList.getLength(); pIndex++) {
                    Node propertyNode = propertiesNodeList.item(pIndex);
                    if (propertyNode instanceof Element) {
                        Element propertyElement = (Element) propertyNode;
                        String name = propertyElement.getAttribute("name");
                        String value = propertyElement.getAttribute("value");
                        String ref = propertyElement.getAttribute("ref");

                        PropertyValue propertyValue = new PropertyValue(name, (null != value && value.length() > 0) ? value : ref);
                        propertyValueList.add(propertyValue);
                    }
                }

                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanClassName(className);
                beanDefinition.setPropertyValues(new PropertyValues(propertyValueList));
                beanDefinitionMap.put(id, beanDefinition);
            }
        }
    }

}
