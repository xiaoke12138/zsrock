package pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLSpirit {
	public static String delHTMLTag(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
		String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
		// String regEX_hchh="\\\\n";
		String regEX_hchh = "<.+?>";
		String regEX_nbsp = "&nbsp";// 去掉nbsp

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签

		Pattern p_hchh = Pattern.compile(regEX_hchh, Pattern.CASE_INSENSITIVE);
		Matcher m_hchh = p_hchh.matcher(htmlStr);
		htmlStr = m_hchh.replaceAll("");

		Pattern p_nbsp = Pattern.compile(regEX_nbsp, Pattern.CASE_INSENSITIVE);
		Matcher m_nbsp = p_nbsp.matcher(htmlStr);
		htmlStr = m_nbsp.replaceAll("");
		return htmlStr.trim(); // 返回文本字符串
	}

	public static void main(String[] args) {
		String s = "<p>原标题：山东：扫黑除恶标本兼治“进行时”</p>\\n<p>今年3月，山东省日照市打掉垄断猪肉屠宰销售市场涉黑犯罪团伙、揪出若干“保护伞”，并全面规范生猪屠宰和肉制品市场，猪肉价格下降近三分之一，当地百姓拍手称快。</p>\\n<p>这是山东省扫黑除恶专项斗争注重“一案三查”、以案治本的一个缩影。“一案三查”主要是指在侦办涉黑恶犯罪案件中既要查办黑恶势力犯罪，又要追查涉黑涉恶腐败问题和“保护伞”，还要倒查党委政府的主体责任和部门的监管责任。</p>\\n<p>打蛇打七寸。扫黑除恶专项斗争的“七寸”，就是要深挖彻查“保护伞”。记者获悉，山东省纪检监察机关与公安机关建立起5日内案情通报情况、侦办案件与查处“保护伞”同步、落实“一案三查”措施的工作机制，为深挖彻查“保护伞”扫清障碍。</p>\\n<p>山东省纪委常委、省监察委员会委员孟祥吉说，山东省纪委监委实行对重点涉黑涉恶腐败问题和“保护伞”提级直办、一线督办、异地办案和交叉办案。对公安机关定性为黑社会性质组织犯罪的，一律由市级纪委监委直接调查处理，对每一个案件都要组成工作专班，深挖细查背后的“保护伞”。对定性为恶势力犯罪集团的，由县级纪委监委直接调查处理，重要案件市级纪委直查。</p>\\n<p>在山东青岛莱西，已提起公诉的以李某庆为首的24人涉嫌黑社会性质组织案，是扫黑除恶专项斗争以来，青岛市首起提起公诉的黑社会性质组织犯罪案件。</p>\\n<p>莱西市政法委常务副书记颜培成说，在李某庆涉黑案中，莱西坚持“一案三查”，除给予李某庆等4名团伙涉案党员开除党籍外，对13名充当“保护伞”的党员干部给予党纪政务处分及组织处理，对1名涉案干部调整岗位，责令镇党委向市委作出检查，对7名党员干部给予批评教育。</p>\\n<p>真正调动人民群众参与扫黑除恶专项斗争的积极性主动性，是打一场扫黑除恶人民战争的关键。</p>\\n<p>“竹板一打连天响，扫黑除恶大家忙……黑恶势力危害大，直接影响现代化……重拳出击为人民，严厉打击不留情……”这段扫黑除恶快板在烟台市福山区22场广场消夏晚会中表演后，大大提升了人民群众对扫黑除恶专项斗争的了解和支持。</p>\\n<p>记者在临沂、青岛、烟台等地采访了解到，全国扫黑除恶专项斗争1月底开展以来，特别是中央扫黑除恶第5督导组8月底进驻山东后，山东各级各部门全面动员，利用路边广告牌、条幅、宣传车、LED屏等广泛宣传扫黑除恶相关内容，营造了铺天盖地的强大声势，扫黑除恶专项斗争深入人心，黑恶势力成为人人喊打的“过街老鼠”。</p>\\n<p>在临沂市费县上冶镇东岭村，记者看到，村中悬挂有多条扫黑除恶的宣传标语，村委会大院也有宣传板，村中不少地方张贴有“上冶镇扫黑除恶专项斗争联系卡”，注明了费县纪检监察、公安、扫黑办等部门联系方式。一位村民说，村委会的大喇叭每天早晚播放严打黑恶违法犯罪的内容，老百姓都知道遇到黑恶势力应该怎么办。</p>\\n<p>记者了解到，中央扫黑除恶第5督导组进驻山东后，在第一时间向社会公布举报电话和邮政信箱的基础上，还创新性专设并公布电子邮箱，建立起电话、信件、网络“三位一体”举报平台，拓宽群众监督举报途径，打通群众监督举报的“最后一公里”。据悉，各举报途径公布后66小时内，共接到群众举报1503件。</p>";
		System.out.println(s);
		org.jsoup.nodes.Document parse = Jsoup.parse(s);
		String text = parse.body().text();
		String replaceAll = text.replaceAll("\\\\n", "").replaceAll("\\\\", "");//去掉\n和文本中的\
		System.out.println(replaceAll);
	}
}
