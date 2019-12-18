package egovframework.com.paid.pet.cntc.igc.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cop.ems.service.impl.EgovSndngMailServiceImpl;
import egovframework.com.paid.pet.cntc.igc.service.CorruptionService;
import egovframework.com.uss.cmt.service.CmtManageVO;
import egovframework.com.uss.cmt.service.impl.EgovCmtManageDAO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * 출퇴근관리에 관한 비지니스 클래스를 정의한다.
 * @author 표준프레임워크 개발팀
 * @since 2009.04.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2009.04.10  개발팀          최초 생성
 *
 * </pre>
 */
@Service("corruptionService")
public class CorruptionServiceImpl extends EgovAbstractServiceImpl implements CorruptionService {


	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSndngMailServiceImpl.class);

	@Override
	public String selectGetCvplCnvrAt(CmtManageVO cmtSearchVO) throws Exception {
		
		XmlParsering xmlParsering = new XmlParsering();
		
		LOGGER.error("#################### xml 요청 메세지  request  ");
		
		try{
			xmlParsering.xmlRequest();
 
        }catch (Exception e){
            e.printStackTrace();
        }
		
		LOGGER.error("#################### xmlparsering start ::: xml 데이터 가져오기  ");
		
		
		try{
			
			xmlParsering.start();
 
        }catch (Exception e){
            e.printStackTrace();
        }
 
		
		return null;
	}
 

}