package me.icro.java.spring.icrospring.iocv2.xml;

import me.icro.java.spring.icrospring.iocv2.AbstractBeanDefinitionReader;
import me.icro.java.spring.icrospring.iocv2.BeanDefinition;
import me.icro.java.spring.icrospring.iocv2.BeanReference;
import me.icro.java.spring.icrospring.iocv2.factory.PropertyValue;
import me.icro.java.spring.icrospring.iocv2.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

/**
 * 描述:
 *
 * @author Lin
 * @since 2019-12-25 2:25 PM
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinition(inputStream);
    }

    protected void doLoadBeanDefinition(InputStream inputStream) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        registerBeanDefinitions(document);
        inputStream.close();
    }

    public void registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();

        parseBeanDefinitions(root);
    }

    protected void parseBeanDefinitions(Element root) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (null != value && 0 < value.length()) {
                    beanDefinition.getPropertyValues().addPropertyValue(PropertyValue.builder().key(name).value(value).build());
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (null == ref || 0 == ref.length()) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref); // 为什么不做初始化？因循环依赖问题，使用lazy-init的方式，当`getBean`时候才初始化。
                    beanDefinition.getPropertyValues().addPropertyValue(PropertyValue.builder().key(name).value(beanReference).build());
                }
            }
        }
    }
}
