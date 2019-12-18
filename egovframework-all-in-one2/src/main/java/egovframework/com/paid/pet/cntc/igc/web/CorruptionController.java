package egovframework.com.paid.pet.cntc.igc.web;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import egovframework.com.cmm.EgovMessageSource;
import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.annotation.IncludedInfo;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.com.paid.pet.cntc.igc.service.CorruptionService;
import egovframework.com.uss.cmt.service.CmtDefaultVO;
import egovframework.com.uss.cmt.service.CmtManageVO;
import egovframework.com.uss.cmt.service.EgovCmtManageService;
import egovframework.com.utl.fcc.service.EgovDateUtil;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.rte.fdl.property.EgovPropertyService;

/**
 * 청렴e시스템 미원정보 연계 서비스 
 * 부패방지종합정보시스템 
 * 청렴, 부패  : Integrity Corruption
 * @author 표준프레임워크 개발팀
 * @since 2014.08.29
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      	수정자           수정내용
 *  -------    --------    ---------------------------
 *   2014.08.29  개발팀          최초 생성
 *   2019.01.10  이정은          출근 중복 확인, 퇴근 전 출근여부 확인 추가
 *
 * </pre>
 */

@Controller
public class CorruptionController {

	/** cmtManageService */
	@Resource(name = "cmtManageService")
	private EgovCmtManageService cmtManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	/** DefaultBeanValidator beanValidator */
	@Autowired
	private CorruptionService corruptionService;

	/** egovCmtManageIdGnrService */
	@Resource(name = "egovCmtManageIdGnrService")
	private EgovIdGnrService idgenService;
	
	/** EgovMessageSource */
	@Resource(name = "egovMessageSource")
	EgovMessageSource egovMessageSource;

	/**
	 * 퇴근 정보를 등록한다.
	 * @param cmtManageVO 사용자등록정보
	 * @param bindingResult 입력값검증용 bindingResult
	 * @param model 화면모델
	 * @return forward:/uss/cmt/EgovCmtMange.do
	 * @throws Exception
	 */
	@RequestMapping(value = "/paid/pet/cntc/igc/GetCvplCnvrAt.do")
	public String selectGetCvplCnvrAt(@ModelAttribute("cmtManageVO") CmtManageVO cmtManageVO, BindingResult bindingResult, Model model) throws Exception {
		//사용자정보
		//KISA 보안약점 조치 (2018-10-29, 윤창원)
		LoginVO user = (LoginVO) EgovUserDetailsHelper.getAuthenticatedUser();
		if (user.getUniqId() != null)
			cmtManageVO.setEmplyrId(user.getUniqId());
		if (user.getOrgnztId() != null)
			cmtManageVO.setOrgnztId(user.getOrgnztId());

		cmtManageVO.setWrktDt(EgovDateUtil.getToday());
		
		String wrktmId = corruptionService.selectGetCvplCnvrAt(cmtManageVO);
		

		return "egovframework/com/paid/pet/cntc/igc/GetCvplCnvrAt";
		
	}

	/**
	 * 출퇴근목록을 조회한다. (paging)
	 * @param userSearchVO 검색조건정보
	 * @param model 화면모델
	 * @return cmm/uss/umt/EgovCmtManageList
	 * @throws Exception
	 */
	@IncludedInfo(name = "출퇴근관리", order = 950, gid = 50)
	@RequestMapping(value = "/paid/pet/cntc/igc/GetCvplCnvrAt2.do")
	public String selectGetCvplCnvrAt2(@ModelAttribute("cmtSearchVO") CmtDefaultVO cmtSearchVO, ModelMap model) throws Exception {

		List<?> cmtManageList = cmtManageService.selectCmtInfoList(cmtSearchVO);
		model.addAttribute("resultList", cmtManageList);

		return "egovframework/com/uss/cmt/EgovCmtManageList";
	}

}
