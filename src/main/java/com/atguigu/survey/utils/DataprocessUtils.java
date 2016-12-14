package com.atguigu.survey.utils;

import java.awt.image.BufferedImage;

import com.atguigu.survey.entities.guest.Bag;
import com.atguigu.survey.entities.guest.Question;
import com.atguigu.survey.entities.manager.Auth;
import com.atguigu.survey.entities.manager.Res;
import com.atguigu.survey.entities.manager.Role;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.jstl.core.LoopTagStatus;

/**
 * 对密码加密的工具类
 * 
 * @author shuai xu 2016年10月17日 下午6:23:27
 */
@SuppressWarnings("all")
public class DataprocessUtils {
	/**
	 * 实现深度复制的通用方法
	 * 
	 * @param source
	 * @return
	 */
	public static Serializable deeplyCopy(Serializable source) {
		if (source == null) {
			return null;
		}
		Serializable target = null;
		ByteArrayOutputStream baos = null;
		ByteArrayInputStream bais = null;
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(source);
			byte[] bufferByteArray = baos.toByteArray();
			bais = new ByteArrayInputStream(bufferByteArray);
			ois = new ObjectInputStream(bais);
			target = (Serializable) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return target;
	}

	/**
	 * 根据源字符串进行加密
	 */
	public static String md5(String source) {
		if (source == null || source.length() == 0) {
			return null;
		}
		// 转化字节数组
		char[] characters = new char[] { '0', '1', '2', '3', '4', '5', '6',
				'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		StringBuilder builder = new StringBuilder();
		byte[] bytes = source.getBytes();
		try {
			// 执行加密
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] bs = digest.digest(bytes);
			for (int i = 0; i < bs.length; i++) {
				byte b = bs[i];
				int high = (b >> 4) & 15;
				int low = b & 15;
				char highChar = characters[high];
				char lowChar = characters[low];
				builder.append(highChar).append(lowChar);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	/**
	 * 将图片压缩按本来的长宽比例压缩为100宽度的jpg图片
	 * 
	 * @param inputStream
	 * @param realPath
	 *            /surveyLogos目录的真实路径，后面没有斜杠
	 * @return 将生成的文件路径返回 surveyLogos/4198393905112.jpg
	 */
	public static String resizeImages(InputStream inputStream, String realPath) {

		OutputStream out = null;

		try {
			// 1.构造原始图片对应的Image对象
			BufferedImage sourceImage = ImageIO.read(inputStream);

			// 2.获取原始图片的宽高值
			int sourceWidth = sourceImage.getWidth();
			int sourceHeight = sourceImage.getHeight();

			// 3.计算目标图片的宽高值
			int targetWidth = sourceWidth;
			int targetHeight = sourceHeight;

			if (sourceWidth > 100) {
				// 按比例压缩目标图片的尺寸
				targetWidth = 100;
				targetHeight = sourceHeight / (sourceWidth / 100);

			}

			// 4.创建压缩后的目标图片对应的Image对象
			BufferedImage targetImage = new BufferedImage(targetWidth,
					targetHeight, BufferedImage.TYPE_INT_RGB);

			// 5.绘制目标图片
			targetImage.getGraphics().drawImage(sourceImage, 0, 0, targetWidth,
					targetHeight, null);

			// 6.构造目标图片文件名
			String targetFileName = System.nanoTime() + ".jpg";

			// 7.创建目标图片对应的输出流
			out = new FileOutputStream(realPath + "/" + targetFileName);

			// 8.获取JPEG图片编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

			// 9.JPEG编码
			encoder.encode(targetImage);

			// 10.返回文件名
			return "surveyLogos/" + targetFileName;

		} catch (Exception e) {

			e.printStackTrace();

			return null;
		} finally {
			// 10.关闭流
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	public static String redisplay(HttpServletRequest request,
			PageContext pageContext, HttpSession session) {
		Bag bag = (Bag) request.getAttribute(GlobaleNames.CURRENT_BAG);
		Integer bagId = bag.getBagId();
		Map<Integer, Map<String, String[]>> allBagMap = (Map<Integer, Map<String, String[]>>) session
				.getAttribute(GlobaleNames.ALL_BAG_MAP);
		Map<String, String[]> paramMap = allBagMap.get(bagId);
		if (paramMap != null) {
			Question question = (Question) pageContext
					.findAttribute("question");
			Integer questionId = question.getQuestionId();
			String inputName = "q" + questionId;
			String[] paramValue = paramMap.get(inputName);
			if (paramValue != null) {
				int questionType = question.getQuestionType();
				if (questionType < 2) {
					List<String> paramValueList = Arrays.asList(paramValue);
					LoopTagStatus status = (LoopTagStatus) pageContext
							.findAttribute("optStatus");
					String index = status.getIndex() + "";
					if (paramValueList.contains(index)) {
						return "checked=checked";
					}
				} else if (questionType == 2) {
					String value = paramValue[0];
					return value;
				}
			}
		}
		return "";
	}

	/**
	 * @param key
	 * @return
	 */
	public static Integer parseQuestionId(String key) {
		String qIdStr = key.substring(1);

		return Integer.parseInt(qIdStr);
	}

	/**
	 * @param value
	 * @return
	 */
	public static String convertArr2Str(String[] value) {
		if (value == null || value.length == 0) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (String string : value) {
			builder.append(string).append(",");
		}
		return removeLast(builder.toString());
	}

	public static String removeLast(String source) {
		if (source == null || source.length() == 0) {
			return source;
		}
		return source.substring(0, source.length() - 1);
	}

	public static String cutServletPath(String servletPath) {
		String[] split = servletPath.split("/");
		return "/" + split[1] + "/" + split[2] + "/" + split[3];
	}

	/**
	 * @param codeArrStr
	 * @param res
	 * @return
	 */
	public static boolean checkAuthority(String codeArrStr, Res res) {
		String[] split = codeArrStr.split(",");
		Integer resCode = res.getResCode();
		Integer resPos = res.getResPos();
		String codeStr = split[resPos];
		Integer code = Integer.parseInt(codeStr);
		Integer result = resCode & code;
		return result != 0;
	}

	public static String calcalateCode(int maxPos, Set<Role> roleSet) {
		int length = maxPos + 1;
		int[] codeArr = new int[length];
		if (roleSet == null && roleSet.size() == 0) {
			return convertCodeArrToStr(codeArr);
		}
		for (Role role : roleSet) {
			Set<Auth> authSet = role.getAuthSet();
			for (Auth auth : authSet) {
				Set<Res> resSet = auth.getResSet();
				for (Res res : resSet) {
					Integer resCode = res.getResCode();
					Integer resPos = res.getResPos();
					int oldValue = codeArr[resPos];
					int newValue = resCode | oldValue;
					codeArr[resPos] = newValue;
				}
			}
		}

		return convertCodeArrToStr(codeArr);
	}

	/**
	 * @param codeArr
	 * @return
	 */
	public static String convertCodeArrToStr(int[] codeArr) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < codeArr.length; i++) {
			int code = codeArr[i];
			builder.append(code).append(",");
		}
		String codeStr = builder.toString();
		return removeLast(codeStr);
	}
	/**
	 * 生成Log数据库表名的工具方法
	 * @param offset
	 * @return
	 */
	public static String generateTableName(int offset){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, offset);
		Date time = calendar.getTime();
		return "AUTO_LOG_"+new SimpleDateFormat("yyyy_MM").format(time);
	}

	/**
	 * @param tableNameList
	 * @return
	 */
	public static String generateSubQuery(List<String> tableNameList) {
		if(tableNameList==null||tableNameList.size()==0){
			return "";
		}
		StringBuilder builder = new StringBuilder();
		for (String string : tableNameList) {
			builder.append("select * from ").append(string).append(" union ");
		}
		String subQuery = builder.toString();
		return subQuery.substring(0,subQuery.lastIndexOf(" union "));
	}
}
